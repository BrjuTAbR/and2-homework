package ru.netology.nmedia

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PostRepositoryFilesImpl(private val context: Context) : PostRepository {

    companion object {
        private const val FILE_NAME = "posts.json"
        private val gson = Gson()
    }

    private val typeToken = TypeToken.getParameterized(List::class.java, Post::class.java).type

    private var nextId = 1L

    private var posts = emptyList<Post>()
        set(value) {
            field = value
            sync()
        }

    private val data = MutableLiveData(posts)

    init {
        val file = context.filesDir.resolve(FILE_NAME)
        if (file.exists()) {
            context.openFileInput(FILE_NAME).bufferedReader().use { it ->
                posts = gson.fromJson(it, typeToken)
                nextId = posts.maxOfOrNull { it.id }?.inc() ?: 1
                data.value = posts
            }
        } else {
            sync()
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
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use{
            it.write(gson.toJson(posts))
        }
    }
}

