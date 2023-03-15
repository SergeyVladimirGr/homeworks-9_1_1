package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        R.id.mainText

        println(R.string.hello)
        println(getString(R.string.hello))
        println(resources.displayMetrics.heightPixels)
        println(resources.displayMetrics.widthPixels)
        println(resources.displayMetrics.densityDpi)
        println(resources.displayMetrics.density)
    }
}
