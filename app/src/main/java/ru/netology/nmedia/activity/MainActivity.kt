package ru.netology.nmedia.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.adapter.onInteractionListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()

    val editingPostContract =
        registerForActivityResult(EditingPostActivity.EditingPostContract) { content ->
            content ?: return@registerForActivityResult
            viewModel.changeContent(content)
            viewModel.save()
            viewModel.cancel()
        }

    private val interactionListener = object : onInteractionListener {

        override fun onEdit(post: Post) {
            viewModel.edit(post)
            editingPostContract.launch(post.content)
        }

        override fun onLike(post: Post) {
            viewModel.like(post.id)
        }

        override fun onShare(post: Post) {
            viewModel.share(post.id) // подсчет репостов
            // формируем интент для отправки данных в систему
            val intent = Intent().apply {
                action = Intent.ACTION_SEND // заполняем интент
                putExtra(Intent.EXTRA_TEXT, post.content) // метод что бы положить данные в интент
                type = "text/plain" // указываем тип
            }
            val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post)) // улучшенный диалог
            startActivity(shareIntent) // посылает запрос в систему на обработку данного интента, запуск другой активити
        }

        override fun onRemove(post: Post) {
            viewModel.removeById(post.id)
        }

        override fun onLaunchVideo(post: Post) {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.ACTION_VIEW, getString(R.string.uri))
                type = "video/*"
            }
            val videoIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
            startActivity(videoIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // регистрируем контракт 1 параметром NewPostContract
        val newPostContract =
            registerForActivityResult(NewPostActivity.NewPostContract) { content ->
                // 2 параметром функция обработчик результата
                // сохраняем результат viewModel и репозитроии
                // вызываются после parsResult
                content ?: return@registerForActivityResult // если null, то выходим из лямды
                // если не null, то вызываем функции отвечающие за создание поста
                viewModel.changeContent(content)
                viewModel.save()
            }
        val adapter = PostsAdapter(interactionListener)
        binding.list.adapter = adapter
        // обработчик observe на все посты вызываеться при любом изменении списка
        viewModel.data.observe(this) { posts ->
            val newPost =
                adapter.currentList.size < posts.size // сравниваем длину списков до и после
            // метод submitList добавляет элементы в recyclerview
            adapter.submitList(posts) {
                if (newPost) {
                    binding.list.smoothScrollToPosition(0) // метод прокрутки на нулевой элемент
                }
            }
        }

        // кнопа добавления нового поста
        binding.add.setOnClickListener {
            // запускаем контракт методом launch()
            newPostContract.launch() // вызовем методом launch() у той переменной которая вернулась функции registerForActivityResult
        }

        /*
viewModel.edited.observe(this) { post ->
    if (post.id != 0L) {
        with(binding.contentInput) {
            requestFocus()
            setText(post.content)

        }
    } else {
        with(binding.contentInput) {
            setText("")
            clearFocus()
            AndroidUtils.hideKeyboard(this)
        }
    }
}
 */
    }
}
