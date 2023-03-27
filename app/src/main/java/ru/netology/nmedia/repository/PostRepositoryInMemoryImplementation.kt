package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImplementation : PostRepository {

    private var posts = listOf(
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar = "",
            published = "18 сентября в 10:12",
            content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \\uD83D\\uDC47",
            lakes = 1_099,
            shareValue = 1_089,
            liked = false
        ),
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar = "",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            lakes = 999,
            shareValue = 989,
            liked = false
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar = "",
            published = "30 сентября в 11:12",
            content = "qwerty",
            lakes = 1_099_999,
            shareValue = 999_999,
            liked = false
        )
    )
    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun like(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(
                liked = !it.liked,
                lakes = it.lakes + if (it.liked) -1 else +1
            )
        }
        data.value = posts
    }

    override fun share(id: Long) {
        posts = posts.map {
            if (it.id != id) it else it.copy(shareValue = it.shareValue + 1)
        }
        data.value = posts
    }
}
