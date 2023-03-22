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
            binding.likeCount.text = valueFormat(post.lakes)
        }

        binding.shareCount.text = post.shareValue.toString()
        binding.share.setOnClickListener {
            post.shareValue++
            binding.shareCount.text = valueFormat(post.shareValue)
            binding.viewsCount.text = valueFormat(post.shareValue)
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

    private fun valueFormat(post: Int): String {
        var result = ""
        if (post in 1..999)
            result = post.toString()
        if (post in 1_000..1_099)
            result = (post / 1_000).toString() + "K"
        if (post in 1_100..9_999)
            result = ((post / 100).toDouble() / 10).toString() + "K"
        if (post in 10_000..999_999)
            result = (post / 1_000).toString() + "K"
        if (post in 1_000_000..1_099_999)
            result = (post / 1_000_000).toString() + "M"
        if (post in 1_100_000..9_999_999)
            result = ((post / 100_000).toDouble() / 10).toString() + "M"
        return result
    }
}
