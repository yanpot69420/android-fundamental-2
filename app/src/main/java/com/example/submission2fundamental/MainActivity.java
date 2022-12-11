package com.example.submission2fundamental;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.databinding.ActivityMainBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.MainViewModel;
import com.example.submission2fundamental.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String url = "https://api.github.com/search/users?q=yanpot";
    private final String TAG = "GITHUB User's Search";
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        binding.fieldInput.clearFocus();
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getUsersLiveData(getUserList(url)).observe(this, getUserCoy);

        binding.fieldInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.fieldInput.clearFocus();
                url = "https://api.github.com/search/users?q=" + query;
                viewModel.setUsersLiveData(getUserList(url));
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.settings) {
            Toast.makeText(this, "Size coy : " + adapter.getSize(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<User> getUserList(String url) {
        return SyncHelper.getUserList(MainActivity.this, url, binding.progressBar, binding.textHolder);
    }

    private Observer<List<User>> getUserCoy = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            adapter = new UserAdapter(users, MainActivity.this);
            binding.rvList.setAdapter(adapter);
        }
    };

}