package com.peeyoosh.navigation_paging_retrofit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.peeyoosh.navigation_paging_retrofit.BR
import com.peeyoosh.navigation_paging_retrofit.R
import com.peeyoosh.navigation_paging_retrofit.databinding.ListItemBinding
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel
import com.squareup.picasso.Picasso

class NewsPagingAdapter (val adapterItemClickListioner: AdapterItemClickListener) :
    PagingDataAdapter<ArticleDataModel, NewsPagingAdapter.MyViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<ArticleDataModel>() {
            override fun areItemsTheSame(
                oldItem: ArticleDataModel,
                newItem: ArticleDataModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ArticleDataModel,
                newItem: ArticleDataModel
            ): Boolean {
                return oldItem.title == newItem.title
            }

        }
    }

    override fun onBindViewHolder(holder: NewsPagingAdapter.MyViewHolder, position: Int) {
        val item = getItem(position)

        holder.viewDataBinding.setVariable(BR.article,item)

        // Not using Binding Adapter here. Access of layout views are shown here.
        Picasso.get().load(item?.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into((holder.viewDataBinding as ListItemBinding).imageListItem)

        (holder.viewDataBinding as ListItemBinding).listItemRoot.setOnClickListener {
            if (item != null) {
                adapterItemClickListioner.clickListener(item)
            }
        }
        
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsPagingAdapter.MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder constructor(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)
}