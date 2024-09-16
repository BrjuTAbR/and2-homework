package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

            override fun onLike(post: Post) {
                viewModel.setLike(post.id)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.setShare(post.id)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                requestFocus()
                setText(post.content)
            }
            with(binding) {
                if (oldContent.text.isNullOrBlank()) {
                    with(oldContent) {
                        setText(content.text)
                        if (!text.isNullOrBlank()) {
                            visibility = View.VISIBLE
                        }
                    }
                } else {
                    hideOldContent(oldContent)
                }
            }
        }

        binding.save.setOnClickListener {

            with(binding.content) {
                if (text.isNullOrBlank()) {

                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }

            if (binding.oldContent.text.isNullOrBlank()) {
                binding.list.scrollToPosition(0)
            }
        }

        binding.escape.setOnClickListener {
            with(binding.content) {
                viewModel.escape()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
            hideOldContent(binding.oldContent)
        }

        with(binding) {
            content.setOnFocusChangeListener { _, hasFocus ->
                val paddingVal = content.paddingStart
                if (hasFocus) {
                    group.visibility = View.VISIBLE
                    content.setPadding(paddingVal, paddingVal * 4, paddingVal, paddingVal)
                } else {
                    group.visibility = View.GONE
                    content.setPadding(paddingVal, paddingVal, paddingVal, paddingVal)
                }

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