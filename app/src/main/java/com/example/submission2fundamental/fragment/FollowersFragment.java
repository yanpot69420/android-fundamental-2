package com.example.submission2fundamental.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.submission2fundamental.databinding.FragmentFollowersBinding;
import com.example.submission2fundamental.helper.SyncHelper;

public class FollowersFragment extends Fragment {

    private FragmentFollowersBinding binding;
    public final String FOLLOWERS_URL;

    public FollowersFragment(String FOLLOWERS_URL) {
        this.FOLLOWERS_URL = FOLLOWERS_URL;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFollowersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvListFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
        SyncHelper.getUserList(getContext(), FOLLOWERS_URL, binding.progressBar, binding.textHolder);
    }
}