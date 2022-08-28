package com.example.pagination.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pagination.R

class LoaderAdapter: LoadStateAdapter<LoaderAdapter.LoadViewHolder>()
{
    class LoadViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val progress = itemView.findViewById<ProgressBar>(R.id.progress_bar)

        fun bind(loadState: LoadState){
            progress.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_loader,parent,false)
        return LoadViewHolder(view)
    }
}