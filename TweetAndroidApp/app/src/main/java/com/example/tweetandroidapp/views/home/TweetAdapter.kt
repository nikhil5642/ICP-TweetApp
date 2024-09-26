package com.example.tweetandroidapp.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.tweetandroidapp.R
import com.example.tweetandroidapp.databinding.TweetItemBinding
import com.example.tweetandroidapp.models.TweetModel

class TweetAdapter : ListAdapter<TweetModel, TweetAdapter.TweetViewHolder>(TweetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val binding: TweetItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tweet_item, parent, false
        )
        return TweetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = getItem(position)
        holder.bind(tweet)
    }

    class TweetViewHolder(private val binding: TweetItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tweet: TweetModel) {
            binding.tweetModel = tweet
            binding.executePendingBindings() // Necessary to apply the data binding
        }
    }

    class TweetDiffCallback : DiffUtil.ItemCallback<TweetModel>() {
        override fun areItemsTheSame(oldItem: TweetModel, newItem: TweetModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TweetModel, newItem: TweetModel): Boolean {
            return oldItem == newItem;
        }
    }
}
