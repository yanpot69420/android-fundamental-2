package com.example.submission2fundamental.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.submission2fundamental.DetailActivity;
import com.example.submission2fundamental.R;
import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.databinding.FragmentFollowersBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FollowersFragment extends Fragment {

    FragmentFollowersBinding binding;
    private String FOLLOWERS_URL;

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
        SyncHelper.getUserList(getContext(), FOLLOWERS_URL, binding.rvListFollowers, binding.progressBar);
    }
}