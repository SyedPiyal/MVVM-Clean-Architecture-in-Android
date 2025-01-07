package com.sample.mvvmcleanarchitecture.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.mvvmcleanarchitecture.data.model.UserItem
import com.sample.mvvmcleanarchitecture.databinding.ItemUserBinding

class UserAdapter(private val users: List<UserItem>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.apply {
            // Assuming you have TextViews for name and email
            nameTextView.text = "${user.first_name} ${user.last_name}"
            emailTextView.text = user.email
            Glide.with(holder.itemView.context).load(user.avatar)
                .into(avatarImageView) // Optional, if you want to load avatars
        }
    }

    override fun getItemCount(): Int = users.size
}
