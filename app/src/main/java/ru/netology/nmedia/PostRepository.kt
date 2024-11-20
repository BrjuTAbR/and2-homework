package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>

    fun setLike(id: Long)
    fun setShare(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
}
