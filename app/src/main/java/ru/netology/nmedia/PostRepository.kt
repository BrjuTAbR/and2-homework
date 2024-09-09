package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun setLike()
    fun setShare()
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = postDataObj

    private val data = MutableLiveData(post)

    override fun get(): MutableLiveData<Post> = data

    override fun setLike() {
        post = post.copy(
            likeByMe = !post.likeByMe,
            likes = post.likes + (if (post.likeByMe) -1 else 1)
        )
        data.value = post
    }

    override fun setShare() {
        post = post.copy(share = post.share + 1)
        data.value = post
    }
}