package ru.netology.nmedia

data class Post(
    val id: Long,
    val title: String,
    val subtitle: String,
    val content: String,
    val likes: Int,
    val share: Int,
    val view: Int,
    val likeByMe: Boolean,
    val video: String?
)
