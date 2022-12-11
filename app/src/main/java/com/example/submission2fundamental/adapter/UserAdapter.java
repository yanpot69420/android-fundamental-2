package com.example.submission2fundamental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.submission2fundamental.DetailActivity;
import com.example.submission2fundamental.databinding.ItemUserBinding;
import com.example.submission2fundamental.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> list;
    private final Context context;

    public UserAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public Integer getSize() {
        return list.size();
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = list.get(position);
        Glide.with(context).load(user.getAvatar()).into(holder.binding.userImage);
        holder.binding.userName.setText(new StringBuilder("@").append(user.getUsername()));
        holder.binding.userId.setText(new StringBuilder("ID : ").append(user.getId()));
        holder.binding.userFollowers.setText(new StringBuilder("Followers : ").append(user.getFollowersCount()));
        holder.binding.userFollowing.setText(new StringBuilder("Following : ").append(user.getFollowingCount()));
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.USER_KEY, user);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}