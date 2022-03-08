package com.android.nytime.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.nytime.R
import com.android.nytime.databinding.MostViewItemBinding

import com.android.nytime.domain.model.Post

import kotlin.properties.Delegates

class MostAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mPostList: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.most_view_item, parent, false
        )
        return PostViewHolder(holderPostBinding)
    }

    override fun getItemCount(): Int = if (mPostList.isNullOrEmpty()) 0 else mPostList.size

    private fun getItem(position: Int): Post = mPostList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(getItem(position))
    }

    private inner class PostViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(post: Post) {
            (viewDataBinding as MostViewItemBinding).post = post
        }
    }
}