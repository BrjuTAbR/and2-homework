package ru.netology.nmedia

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun setLike(id: Long) {
        repository.setLike(id)
    }
    fun setShare(id: Long) {
        repository.setShare(id)
    }

}