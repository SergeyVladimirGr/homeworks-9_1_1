package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val content: String,
    val likes: Int,
    val shareValue: Int,
    val liked: Boolean,
    val video: Boolean
)
