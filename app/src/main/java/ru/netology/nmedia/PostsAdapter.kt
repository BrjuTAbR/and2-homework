package ru.netology.nmedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.CardPostBinding

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            title.text = post.title
            subtitle.text = post.subtitle
            content.text = post.content
            favBtn.text = if (post.likes > 0) getTextFromNum(post.likes) else ""
            shareBtn.text = if (post.share > 0) getTextFromNum(post.share) else ""
            viewCount.text = if (post.view > 0) post.view.toString() else ""

            favBtn.isChecked = post.likeByMe

            favBtn.setOnClickListener {
                onInteractionListener.onLike(post)
            }
            shareBtn.setOnClickListener {
                onInteractionListener.onShare(post)
            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }

            if(post.video != null) {
                videoBox.visibility = View.VISIBLE

                videoBox.setOnClickListener{
                    onInteractionListener.onVideo(post)
                }
            }
            else {
                videoBox.visibility = View.GONE
            }
        }
    }
}
