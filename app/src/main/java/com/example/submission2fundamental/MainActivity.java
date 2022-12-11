package com.example.submission2fundamental;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.example.submission2fundamental.databinding.ActivityMainBinding;
import com.example.submission2fundamental.helper.SyncHelper;

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

        String url = "https://api.github.com/search/users?q=amir";
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        SyncHelper.getUserList(this, url, binding.rvList, binding.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

}