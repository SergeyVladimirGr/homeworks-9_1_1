package ru.netology.dto

data class Post(
    val id: Long = 0,
    val author: String,
    val authorAvatar: String,
    val published: String,
    val content: String,
    var lakes: Int = 999,
    var shareValue: Int = 899,
    var liked: Boolean = false,
)
