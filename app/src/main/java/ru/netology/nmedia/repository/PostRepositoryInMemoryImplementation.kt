package ru.netology.nmedia.repository

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImplementation : PostRepository {

    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        authorAvatar = "",
        published = "21 мая в 18:36",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        lakes = 999,
        shareValue = 989,
        liked = false
    )
    private val data = MutableLiveData(post)

    override fun get() = data
    override fun like() {
        post = post.copy(liked = !post.liked, lakes = post.lakes + if (post.liked) -1 else 1)
        data.value = post
    }

    override fun share() {
        post = post.copy(shareValue = post.shareValue + 1)
        data.value = post
    }
}
