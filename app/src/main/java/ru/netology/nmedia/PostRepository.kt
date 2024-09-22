package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>

    fun setLike(id: Long)
    fun setShare(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
}

class PostRepositoryInMemoryImpl : PostRepository {

    private var nextId = posts.size.toLong() + 1L

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
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

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

}