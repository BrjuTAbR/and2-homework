package ru.netology.nmedia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =  ActivityNewPostBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        binding.ok.backgroundTint


        binding.ok.setOnClickListener {
            val text = binding.edit.text.toString()

            if(text.isBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().apply {

                    putExtra(KEY_TEXT, text)
                })
            }
            finish()
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

object NewPostContract : ActivityResultContract<Unit, String?>() {
    override fun createIntent(context: Context, input: Unit) = Intent(context, NewPostActivity::class.java)


    override fun parseResult(resultCode: Int, intent: Intent?): String? = intent?.getStringExtra(NewPostActivity.KEY_TEXT )

}