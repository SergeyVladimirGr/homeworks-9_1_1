package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonOk.setOnClickListener {
            val text = binding.content.text.toString() // формируем text
            if (text.isBlank()) { // если текст пустой, то возвращаем RESULT_CANCELED
                setResult(Activity.RESULT_CANCELED) // для возврата данных вызываем setResult и finish()
            } else {
                // если не пустой записываем текст в text методом putExtra (ключ - значение), вызываем setResult с успешным кодом и возвращаем text
                setResult(Activity.RESULT_OK, Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
            }
            finish() // скрывается текущая активити и появляется предыдущая
        }
    }

    // создаем контракт
    object NewPostContract : ActivityResultContract<Unit, String?>() {
        // имплементируем метод на формирование Intent
        // при запуске контракта методом launch() - вызывается метод createIntent и формируется Intent, появляется активити
        // метод на отправку данных
        override fun createIntent(context: Context, input: Unit): Intent = // на вход данные которые передаются в контракт, на выход созданный класс интент
            Intent(context, NewPostActivity::class.java) // вызываем конструктор и передаем в него ссылку на другую активити, интент явный поэтому ни action, ни type задавать не надо

        // имплементируем метод на получение результата
        // начинает выполняться контракт, вызывается метод parseResult на вход принимает setResult
        // метод на получения данных из той активити
        override fun parseResult(resultCode: Int, intent: Intent?): String? =
            if (resultCode == Activity.RESULT_OK) { // если код завершения ок, то получаем данные из intent, иначе возвращаем null
                intent?.getStringExtra(Intent.EXTRA_TEXT)
            } else {
                null
            }
    }
}
