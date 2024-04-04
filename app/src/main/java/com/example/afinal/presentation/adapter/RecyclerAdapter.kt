package com.example.afinal.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.databinding.RowsBinding

class RecyclerAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ClothesItem, RecyclerAdapter.MyViewHolder>(SampleItemDiffCallback()) {
    class OnClickListener(val clickListener: (clothes: ClothesItem) -> Unit) {
        fun onClick(clothes: ClothesItem) = clickListener(clothes)
    }

    class MyViewHolder(val clothesBinding: RowsBinding) :
        RecyclerView.ViewHolder(clothesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RowsBinding.inflate(view, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val shoppingList = getItem(position)

        holder.clothesBinding.textViewName.text = shoppingList.title
        holder.clothesBinding.textViewCathegory.text = shoppingList.category
        holder.clothesBinding.textViewPrice.text = shoppingList.price.toString()

        Glide.with(holder.itemView.context).load(shoppingList.image)
            .into(holder.clothesBinding.imageViewClothes)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(shoppingList)
        }
    }
}

class SampleItemDiffCallback : DiffUtil.ItemCallback<ClothesItem>() {

    override fun areItemsTheSame(oldItem: ClothesItem, newItem: ClothesItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ClothesItem, newItem: ClothesItem): Boolean =
        oldItem == newItem
}
