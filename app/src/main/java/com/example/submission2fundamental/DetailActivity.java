package com.example.submission2fundamental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submission2fundamental.adapter.FragmentAdapter;
import com.example.submission2fundamental.databinding.ActivityDetailBinding;
import com.example.submission2fundamental.helper.DatabaseHelper;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;
import com.example.submission2fundamental.model.UserDetail;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    public final static String USER_KEY = "USER_KEY";
    private final String TAG = "Detail User";
    private User user;
    private UserDetail userDetail;
    DatabaseHelper data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data = new DatabaseHelper(this);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }

        user = getIntent().getParcelableExtra(USER_KEY);
        SyncHelper.getAdditionalUserDetail(this, user.getUrl(), binding);
        addUserView();
        setupFragment();
        configureFavoriteBtn();
        binding.detailBtn.setOnClickListener(v -> {
            if(data.getUser(user.getId())) {
                if(data.removeFavorites(user.getId())) {
                    Toast.makeText(this, "User removed from favorite list", Toast.LENGTH_SHORT).show();
                    binding.detailBtn.clearColorFilter();
                }
                else {
                    Toast.makeText(this, "Failed to remove user", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                if(data.addFavorites(user)) {
                    Toast.makeText(this, "User added to favorite list", Toast.LENGTH_SHORT).show();
                    binding.detailBtn.setColorFilter(Color.RED);
                }
                else {
                    Toast.makeText(this,"Failed to add user to favorite", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configureFavoriteBtn();
    }

    void setupFragment() {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), user.getUrl());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.favorites) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void addUserView() {
        Glide.with(this).load(user.getAvatar()).into(binding.detailImage);
        binding.detailUsername.setText(new StringBuilder("@").append(user.getUsername()));
    }

    private void configureFavoriteBtn() {

        if(data.getUser(user.getId())) {
            binding.detailBtn.setColorFilter(Color.RED);
        }
        else {
            binding.detailBtn.clearColorFilter();
        }
    }
}