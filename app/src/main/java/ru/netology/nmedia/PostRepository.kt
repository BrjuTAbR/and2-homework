package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun setCount(str: String)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = postDataObj

    private val data = MutableLiveData(post)

    override fun get(): MutableLiveData<Post> = data

    override fun setCount(str: String) {
        when (str) {
            "like" -> {
                post = post.copy(
                    likeByMe = !post.likeByMe,
                    likes = post.likes + (if (post.likeByMe) -1 else 1)
                )
            }
            "share" -> post = post.copy(share = post.share + 1)
        }
        data.value = post
    }
}