package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImplementation

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImplementation()

    val data = repository.getAll()
    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
}
