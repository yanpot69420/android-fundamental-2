package com.example.submission2fundamental;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.databinding.ActivityMainBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.User;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "GITHUB User's Search";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.fieldInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.fieldInput.clearFocus();
                String searchUrl = "https://api.github.com/search/users?q=" + query;
                configListAdapter(searchUrl);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    private void configListAdapter(String searchUrl) {
        UserAdapter adapter = new UserAdapter(SyncHelper.getUserList(MainActivity.this, searchUrl, binding.progressBar, binding.textHolder), MainActivity.this);
        binding.rvList.setAdapter(adapter);
    }

}