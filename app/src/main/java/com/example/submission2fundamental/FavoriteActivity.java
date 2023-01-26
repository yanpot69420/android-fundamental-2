package com.example.submission2fundamental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.submission2fundamental.adapter.FavoriteAdapter;
import com.example.submission2fundamental.databinding.ActivityFavoriteBinding;
import com.example.submission2fundamental.helper.DatabaseHelper;
import com.example.submission2fundamental.model.User;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private final String TAG = "Favorite Users";
    ActivityFavoriteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FavoriteAdapter adapter = new FavoriteAdapter(getFavorites(), this);
        binding.rvFavorite.setAdapter(adapter);
        binding.rvFavorite.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<User> getFavorites() {
        DatabaseHelper data = new DatabaseHelper(this);
        return data.getFavoriteUsers();
    }
}