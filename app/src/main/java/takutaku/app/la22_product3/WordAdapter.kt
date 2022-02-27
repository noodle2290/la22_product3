package takutaku.app.la22_product3

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import takutaku.app.la22_product3.databinding.WordItemBinding

class WordAdapter(private val onClickListener: OnClickListener): ListAdapter<Word, WordViewHolder>(diffUtilItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = WordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(word)
        }
    }
}

class WordViewHolder(
    private val binding: WordItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(word: Word) {
        binding.titleTextView.text = word.title
        binding.contentTextView.text = word.content
    }
}

private val diffUtilItemCallback = object : DiffUtil.ItemCallback<Word>() {
    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }
}

class OnClickListener(val clickListener: (word: Word) -> Unit) {
    fun onClick(word: Word) = clickListener(word)
}