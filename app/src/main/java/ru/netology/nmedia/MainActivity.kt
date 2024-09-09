package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { post ->
            with(binding) {
                title.text = post.title
                subtitle.text = post.subtitle
                content.text = post.content
                favCount.text = getTextFromNum(post.likes)
                shareCount.text = getTextFromNum(post.share)
                viewCount.text = getTextFromNum(post.view)
                favBtn.setImageResource(getLikeImg(post.likeByMe))
            }
        }

        with(binding) {
            favBtn.setOnClickListener {
                viewModel.setLike()
            }
            shareBtn.setOnClickListener {
                viewModel.setShare()
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