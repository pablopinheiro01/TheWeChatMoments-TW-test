package com.tws.moments.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.TWApplication
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.databinding.ItemMomentHeadBinding

class HeaderViewHolder(
    private val binding: ItemMomentHeadBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var imageLoader = TWApplication.imageLoader

    fun bind(userBean: UserBean?) {
        imageLoader.displayImage(userBean?.profileImage, binding.ivUserProfile)
        imageLoader.displayImage(userBean?.avatar, binding.ivUserAvatar)
        binding.tvUserNickname.text = userBean?.nick
    }
}