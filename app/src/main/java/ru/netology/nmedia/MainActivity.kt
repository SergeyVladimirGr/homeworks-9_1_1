package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.dto.Post
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            author = "Нетология. Университет интернет-профессий будущего",
            authorAvatar = "",
            published = "21 мая в 18:36",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )
        binding.author.text = post.author
        binding.published.text = post.published
        binding.content.text = post.content

        binding.likeCount.text = post.lakes.toString()
        binding.like.setOnClickListener {
            post.liked = !post.liked
            binding.like.setImageResource(
                if (post.liked) R.drawable.ic_likes_24 else R.drawable.ic_like_24
            )
            if (post.liked) post.lakes++ else post.lakes--
            binding.likeCount.text = likeValueFormat(post)
        }

        binding.shareCount.text = post.shareValue.toString()
        binding.share.setOnClickListener {
            post.shareValue++
            binding.shareCount.text = shareValueFormat(post)
            binding.viewsCount.text = shareValueFormat(post)
        }
        binding.viewsCount.text = post.shareValue.toString()

        /*
       findViewById<ImageView>(R.id.like).setOnClickListener {
       (it as ImageView).setImageResource(R.drawable.baseline_favorite_24)
       println("click")
       }
       */
/*
        binding.root.setOnClickListener {
            Log.d("stuff", "stuff")
        }

        binding.avatar.setOnClickListener {
            Log.d("stuff", "avatar")
        }

        binding.like?.setOnClickListener {
            Log.d("stuff", "like")
        }
*/
        /*
        println(resources.displayMetrics.heightPixels)
        println(resources.displayMetrics.widthPixels)
        println(resources.displayMetrics.densityDpi)
        println(resources.displayMetrics.density)
        */
    }

    fun likeValueFormat(post: Post): String {
        var result = ""
        if (post.lakes in 1..999)
            result = post.lakes.toString()
        if (post.lakes in 1_000..1_099)
            result = (post.lakes / 1_000).toString() + "K"
        if (post.lakes in 1_100..9_999)
            result = ((post.lakes / 100).toDouble() / 10).toString() + "K"
        if (post.lakes in 10_000..999_999)
            result = (post.lakes / 1_000).toString() + "K"
        if (post.lakes in 1_000_000..1_099_999)
            result = (post.lakes / 1_000_000).toString() + "M"
        if (post.lakes in 1_100_000..9_999_999)
            result = ((post.lakes / 100_000).toDouble() / 10).toString() + "M"
        return result
    }

    fun shareValueFormat(post: Post): String {
        var result = ""
        if (post.shareValue in 1..999)
            result = post.shareValue.toString()
        if (post.shareValue in 1_000..1_099)
            result = (post.shareValue / 1_000).toString() + "K"
        if (post.shareValue in 1_100..9_999)
            result = ((post.shareValue / 100).toDouble() / 10).toString() + "K"
        if (post.shareValue in 10_000..999_999)
            result = (post.shareValue / 1_000).toString() + "K"
        if (post.shareValue in 1_000_000..1_099_999)
            result = (post.shareValue / 1_000_000).toString() + "M"
        if (post.shareValue in 1_100_000..9_999_999)
            result = ((post.shareValue / 100_000).toDouble() / 10).toString() + "M"
        return result
    }
}
