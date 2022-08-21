package com.df.android.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.df.android.data.model.GithubUserDto
import com.df.android.databinding.ItemUserBinding

class GithubUserAdapter(
    var onClickListener: ((user: GithubUserDto) -> Unit)? = null
) : ListAdapter<GithubUserDto, GithubUserAdapter.DataViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DataViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubUserDto) {
            binding.apply {
                textUsername.text = item.login
                textHref.text = item.htmlUrl
                Glide.with(imageAvatar.context)
                    .load(item.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageAvatar)
            }

            itemView.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GithubUserDto>() {
            override fun areItemsTheSame(oldItem: GithubUserDto, newItem: GithubUserDto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubUserDto, newItem: GithubUserDto): Boolean {
                return oldItem == newItem
            }
        }
    }

}