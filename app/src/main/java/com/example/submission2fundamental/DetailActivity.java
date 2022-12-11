package com.example.submission2fundamental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.example.submission2fundamental.adapter.FragmentAdapter;
import com.example.submission2fundamental.databinding.ActivityDetailBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    public final static String USER_KEY = "USER_KEY";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(USER_KEY);
        addUserView();
        setupFragment();
    }

    void setupFragment() {
        Integer followersCount = SyncHelper.countFollow(user.getFollowers());
        Integer followingCount = SyncHelper.countFollow(user.getFollowing());
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0).setText("FOLLOWERS (" + followersCount + ")"));
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1).setText("FOLLOWING (" + followingCount + ")"));
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), user.getFollowers(), user.getFollowing());
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

    void addUserView() {
        Glide.with(this).load(user.getAvatar()).into(binding.detailImage);
        binding.detailUsername.setText(new StringBuilder("@").append(user.getUsername()));
        binding.detailId.setText(new StringBuilder("ID : ").append(user.getId()));
    }


}