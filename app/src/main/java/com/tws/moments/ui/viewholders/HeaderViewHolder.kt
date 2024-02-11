package com.tws.moments.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.TWApplication
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.databinding.ItemMomentHeadBinding

class HeaderViewHolder(private val binding: ItemMomentHeadBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var imageLoader = TWApplication.imageLoader

    fun bind(userBean: UserBean?) {

    }
}