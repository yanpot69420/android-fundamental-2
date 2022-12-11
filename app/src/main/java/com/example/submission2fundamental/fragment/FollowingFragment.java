package com.example.submission2fundamental.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.submission2fundamental.databinding.FragmentFollowingBinding;
import com.example.submission2fundamental.helper.SyncHelper;

public class FollowingFragment extends Fragment {

    FragmentFollowingBinding binding;

    private final String FOLLOWING_URL;

    public FollowingFragment(String FOLLOWING_URL) {
        this.FOLLOWING_URL = FOLLOWING_URL;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFollowingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvListFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
        SyncHelper.getUserList(getActivity(), FOLLOWING_URL, binding.progressBar, binding.textHolder);
    }
}