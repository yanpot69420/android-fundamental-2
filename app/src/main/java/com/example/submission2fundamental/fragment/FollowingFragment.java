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

import com.example.submission2fundamental.MainActivity;
import com.example.submission2fundamental.R;
import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.databinding.FragmentFollowingBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

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
        Toast.makeText(getContext(), FOLLOWING_URL, Toast.LENGTH_SHORT).show();
        binding.rvListFollowing.setLayoutManager(new LinearLayoutManager(getContext()));
        SyncHelper.getUserList(getActivity(), FOLLOWING_URL, binding.rvListFollowing, binding.progressBar);
    }
}