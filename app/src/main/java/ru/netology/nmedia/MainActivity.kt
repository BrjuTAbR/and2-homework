package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.ru.netology.nmedia.*
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            title.text = post.title
            subtitle.text = post.subtitle
            content.text = post.content
            favCount.text = getTextFromNum(post.likes)
            shareCount.text = getTextFromNum(post.share)
            viewCount.text = getTextFromNum(post.view)

            favBtn.setImageResource(getLikeImg(post.likeByMe))

            favBtn.setOnClickListener{
                post.likeByMe = !post.likeByMe
                favBtn.setImageResource(getLikeImg(post.likeByMe))
                if(post.likeByMe) post.likes++  else max(post.likes--, 0)
                favCount.text = getTextFromNum(post.likes)
            }
            shareBtn.setOnClickListener{
                post.share++
                shareCount.text = getTextFromNum(post.share)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val paddingVal = v.paddingStart

            v.setPadding(paddingVal, systemBars.top, paddingVal, systemBars.bottom)
            insets
        }
    }
}