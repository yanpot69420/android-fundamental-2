package com.example.submission2fundamental.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.submission2fundamental.databinding.FragmentFollowingBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.FollowViewModel;

public class FollowingFragment extends Fragment {

    FragmentFollowingBinding binding;
    private FollowViewModel viewModel;

    public FollowingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static FollowingFragment newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        FollowingFragment fragment = new FollowingFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        viewModel = new ViewModelProvider(this).get(FollowViewModel.class);
        viewModel.setUrlFollowers(getArguments().getString("URL"));
        SyncHelper.getUserList(getActivity(), viewModel.getUrlFollowers(), binding.progressBar, binding.textHolder, binding.rvListFollowing);
    }
}