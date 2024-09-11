package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>

    fun setLike(id: Long)
    fun setShare(id: Long)
}

class PostRepositoryInMemoryImpl : PostRepository {

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun setLike(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            }
            else {
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
}