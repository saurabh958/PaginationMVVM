package com.example.pagination.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.example.pagination.adapters.EmployeeDataAdapter.MyViewHolder
import com.example.pagination.databinding.ItemEmployeeDetailBinding
import com.example.pagination.model.Data
import com.example.pagination.model.EmployeeData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeDataAdapter: PagingDataAdapter<Data, MyViewHolder>(differCallback) {

    inner class MyViewHolder(val binding: ItemEmployeeDetailBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        val differCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvFullName.text = "${currentItem?.first_name ?: ""} ${currentItem?.last_name ?: ""}"
            tvEmail.text = currentItem?.email ?: ""
            val imagelink = currentItem?.avatar ?: ""
            imgProfile.load(imagelink){
                crossfade(true)
                crossfade(1000)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemEmployeeDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
}