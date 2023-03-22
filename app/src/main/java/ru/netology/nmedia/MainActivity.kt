package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                like.setImageResource(
                    if (post.liked) R.drawable.ic_likes_24 else R.drawable.ic_like_24
                )
                likeCount.text = valueFormat(post.lakes)
                shareCount.text = valueFormat(post.shareValue)
                viewsCount.text = valueFormat(post.shareValue)
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }
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
