package com.example.submission2fundamental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.bumptech.glide.Glide;
import com.example.submission2fundamental.adapter.FragmentAdapter;
import com.example.submission2fundamental.databinding.ActivityDetailBinding;
import com.example.submission2fundamental.fragment.FollowersFragment;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    public final static String USER_KEY = "USER_KEY";
    private final String TAG = "Detail User";
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }

        user = getIntent().getParcelableExtra(USER_KEY);
        addUserView();
        setupFragment();
    }

    void setupFragment() {

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

        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0).setText("FOLLOWERS (" + user.getFollowersCount() + ")"));
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1).setText("FOLLOWING (" + user.getFollowingCount() + ")"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    void addUserView() {
        Glide.with(this).load(user.getAvatar()).into(binding.detailImage);
        binding.detailUsername.setText(new StringBuilder("@").append(user.getUsername()));
        binding.detailId.setText(new StringBuilder("ID : ").append(user.getId()));
    }


}