package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListHyoukaBinding
import com.example.core.domain.model.Hyouka
import java.lang.StringBuilder

class ItemListAdapter: RecyclerView.Adapter<ItemListAdapter.ListViewHolder>() {

    private var listData = ArrayList<Hyouka>()
    var onItemClick: ((Hyouka) -> Unit)? = null

    fun setData(newListData: List<Hyouka>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListHyoukaBinding.bind(itemView)
        val stringBuilder = StringBuilder()
        fun bind(data: Hyouka) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(data.image_url)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemInfo.text = "Episodes: ${data.episodes}"
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_hyouka, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size
}