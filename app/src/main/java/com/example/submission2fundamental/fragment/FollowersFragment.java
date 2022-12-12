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
import android.widget.Toast;

import com.example.submission2fundamental.databinding.FragmentFollowersBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.FollowersViewModel;

public class FollowersFragment extends Fragment {

    private FragmentFollowersBinding binding;
    private FollowersViewModel viewModel;

    public FollowersFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static FollowersFragment newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("URL", url);
        FollowersFragment fragment = new FollowersFragment();
        fragment.setArguments(bundle);
        return fragment;
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
        Toast.makeText(getContext(), "viewCreated", Toast.LENGTH_SHORT).show();
        binding.rvListFollowers.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(FollowersViewModel.class);
        viewModel.setUrlFollowers(getArguments().getString("URL"));
        SyncHelper.getUserList(getContext(), viewModel.getUrlFollowers(), binding.progressBar, binding.textHolder, binding.rvListFollowers);
    }
}