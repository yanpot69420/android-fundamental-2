package com.example.submission2fundamental.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.submission2fundamental.fragment.FollowersFragment;
import com.example.submission2fundamental.fragment.FollowingFragment;

public class FragmentAdapter extends FragmentStateAdapter {

    private final String followersLink, followingLink;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, String followersLink, String followingLink) {
        super(fragmentManager, lifecycle);
        this.followersLink = followersLink;
        this.followingLink = followingLink;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 1:
                fragment = FollowingFragment.newInstance(followingLink);
                break;
            case 0:
            default:
                fragment = FollowersFragment.newInstance(followersLink);
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
