package ru.netology.nmedia

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositorySharedPrefsImpl(context: Context) : PostRepository {

    companion object {
        private const val KEY = "posts"
        private val gson = Gson()
    }

    private val prefs = context.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private val typeToken = TypeToken.getParameterized(List::class.java, Post::class.java).type

    private var nextId = 1L

    private var posts = emptyList<Post>()
        set(value) {
            field = value
            sync()
        }

    private val data = MutableLiveData(posts)

    init {
        prefs.getString(KEY, null)?.let {
            posts = gson.fromJson(it, typeToken)
            nextId = posts.maxOfOrNull { it.id }?.inc() ?: 1
            data.value = posts
        }
    }


    override fun getAll(): LiveData<List<Post>> = data

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    title = "Me",
                    likeByMe = false,
                    subtitle = "now"
                )
            ) + posts
            data.value = posts

            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts

    }

    override fun setLike(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                val likesIncrement = if (it.likeByMe) -1 else 1
                it.copy(likeByMe = !it.likeByMe, likes = it.likes + likesIncrement)
            }
        }
        data.value = posts

    }

    override fun setShare(id: Long) {
        posts = posts.map {
            if (it.id != id)
                it
            else {
                it.copy(share = it.share + 1)
            }
        }
        data.value = posts

    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    private fun sync() {
        with(prefs.edit()) {
            putString(KEY, gson.toJson(posts))
            apply()
        }
    }


}

