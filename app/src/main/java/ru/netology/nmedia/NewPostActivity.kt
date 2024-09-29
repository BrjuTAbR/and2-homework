package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.NewPostActivity.Companion.KEY_TEXT
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNewPostBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val txt = intent.getStringExtra(KEY_TEXT)

        binding.edit.setText(txt)

        val viewModel: PostViewModel by viewModels()

        binding.ok.setOnClickListener {
            val text = binding.edit.text.toString()

            if (text.isBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().apply {

                    putExtra(KEY_TEXT, text)
                })
            }
            finish()
        }

        binding.escape.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.edit) {
                requestFocus()
                setText(post.content)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.new_post)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val paddingVal = v.paddingStart

            v.setPadding(paddingVal, systemBars.top, paddingVal, paddingVal)
            insets
        }
    }


    companion object {
        const val KEY_TEXT = "text"
    }

}

object NewPostContract : ActivityResultContract<String?, String?>() {
    override fun createIntent(context: Context, input: String?) =
        Intent(context, NewPostActivity::class.java).putExtra("text", input)


    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        intent?.getStringExtra(NewPostActivity.KEY_TEXT)
}