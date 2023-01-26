package com.example.submission2fundamental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.submission2fundamental.databinding.ActivityMainBinding;
import com.example.submission2fundamental.helper.SyncHelper;
import com.example.submission2fundamental.model.MainViewModel;
import com.example.submission2fundamental.model.User;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "GITHUB User's Search";
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setTitle(TAG);
        }

        binding.fieldInput.clearFocus();
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getUsersLiveData(getUserList(viewModel.getUrlModel()));

        binding.fieldInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.fieldInput.clearFocus();
                viewModel.setUrlModel("https://api.github.com/search/users?q="+query);
                viewModel.setUsersLiveData(getUserList(viewModel.getUrlModel()));
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
        if(item.getItemId() == R.id.favorites) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private List<User> getUserList(String url) {
        return SyncHelper.getUserList(MainActivity.this, url, binding.progressBar, binding.textHolder, binding.rvList);
    }
}