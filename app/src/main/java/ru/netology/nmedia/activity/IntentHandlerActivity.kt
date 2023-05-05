package ru.netology.nmedia.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityIntentHandlerBinding

class IntentHandlerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityIntentHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // обработчик данных которые к нам приходят
        intent?.let {
            if (it.action != Intent.ACTION_SEND)
                return@let // если не action выходим из лямды
        }
        val text = intent.getStringExtra(Intent.EXTRA_TEXT) // если action, то получаем текст
        if (text.isNullOrBlank()) // если текст пустой выдаем Snack bar
            Snackbar.make(binding.root, R.string.error_empty_content, Snackbar.LENGTH_INDEFINITE) // в метод make передаем параметры LENGTH_INDEFINITE сколько времени показывать
                .setAction(android.R.string.ok) { // добовляем кнопку, при нажатии ok скрываем Snack bar
                    finish() // если text пустой завершает работу текущей активити
                }
                .show() // вызываем метод .show() для показа Snack bar
    }
    // TODO handle text
}
