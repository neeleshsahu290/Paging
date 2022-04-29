package com.scriptsbundle.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.scriptsbundle.paging.databinding.UserCardViewBinding
import com.scriptsbundle.paging.db.User
import com.scriptsbundle.paging.modelclass.Item

class UserAdapter(val itemlist:ArrayList<User>?=null, val context: Context): RecyclerView.Adapter<UserAdapter.userviewholder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card_view,parent,false)
        return userviewholder(view)    }

    override fun onBindViewHolder(holder: userviewholder, position: Int) {
        val list = itemlist?.get(position)
        holder.binding.textname.text=list?.display_name
        val url = list?.profile_image
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_avatar_24)
            .into(holder.binding.profileImage)

    }

    override fun getItemCount(): Int {
    return itemlist?.size?:0
    }

    inner class userviewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val binding =  UserCardViewBinding.bind(itemView)


    }
}