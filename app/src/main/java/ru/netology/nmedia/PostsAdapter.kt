package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.CardPostBinding

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(private val onLikeListener: OnLikeListener, private val onShareListener: OnShareListener) : RecyclerView.Adapter<PostViewHolder>() {
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            title.text = post.title
            subtitle.text = post.subtitle
            content.text = post.content
            favCount.text = if (post.likes > 0) post.likes.toString() else ""
            shareCount.text = if (post.share > 0) post.share.toString() else ""
            viewCount.text = if (post.view > 0) post.view.toString() else ""

            if (post.likeByMe) {
                favBtn.setImageResource(R.drawable.ic_favorite)
            } else {
                favBtn.setImageResource(R.drawable.ic_favorite_border)
            }

            favBtn.setOnClickListener{
                onLikeListener(post)
            }
            shareBtn.setOnClickListener{
                onShareListener(post)
            }
        }
    }
}
