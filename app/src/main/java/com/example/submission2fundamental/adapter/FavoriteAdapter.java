package com.example.submission2fundamental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission2fundamental.databinding.ItemFavoriteBinding;
import com.example.submission2fundamental.helper.DatabaseHelper;
import com.example.submission2fundamental.model.User;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<User> favorites;
    private final Context context;

    public FavoriteAdapter(List<User> favorites, Context context) {
        this.favorites = favorites;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        User user = favorites.get(position);
        DatabaseHelper data = new DatabaseHelper(context);
        Glide.with(context).load(user.getAvatar()).into(holder.binding.userImage);
        holder.binding.userName.setText(new StringBuilder("@").append(user.getUsername()));
        holder.binding.userId.setText(new StringBuilder("ID : ").append(user.getId()));
        holder.binding.userType.setText(new StringBuilder("Type : ").append(user.getType()));
        holder.binding.imageButton.setOnClickListener(v -> {
            data.removeFavorites(user.getId());
            favorites.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, user.getUsername() + " removed from favorite list", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFavoriteBinding binding;
        public ViewHolder(ItemFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
