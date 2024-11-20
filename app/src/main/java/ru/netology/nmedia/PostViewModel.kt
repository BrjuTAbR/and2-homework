package ru.netology.nmedia

import androidx.lifecycle.*

class PostViewModel : ViewModel() {
    private val empty = Post(
        id = 0,
        content = "",
        title = "",
        likeByMe = false,
        subtitle = "",
        likes = 0,
        share = 0,
        view = 0,
        video = null
    )

    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun setLike(id: Long) {
        repository.setLike(id)
    }
    fun setShare(id: Long) {
        repository.setShare(id)
    }

    fun removeById(id: Long) = repository.removeById(id)

    fun edit(post: Post) {
        edited.value = post
    }

    fun save() {
        edited.value?.let {
            repository.save(it)

        }
        escape()
    }
    fun escape () {
        edited.value = empty
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }


}