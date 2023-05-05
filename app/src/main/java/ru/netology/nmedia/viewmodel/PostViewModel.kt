package ru.netology.nmedia.viewmodel

import android.provider.MediaStore.Video
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImplementation

private val empty = Post(
    id = 0,
    author = "",
    authorAvatar = "",
    published = "",
    content = "",
    likes = 0,
    shareValue = 0,
    liked = false,
    video = false
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImplementation()
    val data = repository.getAll()

    // текущий редактируемый пост. если empty, значит готовый к добавлению записи
    private val edited = MutableLiveData(empty)
    
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }

    // функция создания поста
    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun like(id: Long) = repository.like(id)
    fun share(id: Long) = repository.share(id)
    fun removeById(id: Long) = repository.removeById(id)
    fun cancel() {
        edited.value = empty
    }
    fun edit(post: Post) {
        edited.value = post
    }
}
