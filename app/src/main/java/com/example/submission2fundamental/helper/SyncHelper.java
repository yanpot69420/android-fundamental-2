package com.example.submission2fundamental.helper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.submission2fundamental.BuildConfig;
import com.example.submission2fundamental.MainActivity;
import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.builder.UserDetailBuilder;
import com.example.submission2fundamental.databinding.ActivityDetailBinding;
import com.example.submission2fundamental.model.User;
import com.example.submission2fundamental.model.UserDetail;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SyncHelper {

    public static List<User> getUserList(Context context, String url, ProgressBar progressBar, TextView textHolder, RecyclerView recyclerView) {
        List<User> userList = new ArrayList<>();
        textHolder.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", BuildConfig.API_KEY);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);
                String result = new String(responseBody);
                JSONObject responseObject;
                JSONArray responseArray;
                Log.wtf("SyncHelper", result);
                try {
                    if(context instanceof MainActivity) {
                        responseObject = new JSONObject(result);
                        responseArray = responseObject.getJSONArray("items");
                    }
                    else {
                        responseArray = new JSONArray(result);
                    }

                    for (int i = 0; i < responseArray.length(); i++) {
                        JSONObject object = responseArray.getJSONObject(i);
                        String username = object.getString("login");
                        String id = object.getString("id");
                        String avatar = object.getString("avatar_url");
                        String type = object.getString("type");
                        String url = object.getString("url");
                        User user;
                        user = new User(avatar, id, username, type, url);
                        userList.add(user);
                    }
                    UserAdapter adapter = new UserAdapter(userList, context);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.wtf("TAG", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                textHolder.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        return userList;
    }

    public static void getAdditionalUserDetail(Context context, String url, ActivityDetailBinding binding) {
        UserDetailBuilder builder = new UserDetailBuilder();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", BuildConfig.API_KEY);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                Log.wtf("SyncHelper", result);
                try {
                    JSONObject responseObject = new JSONObject(result);
                    String id = responseObject.getString("id");
                    String name = responseObject.getString("name");
                    String location = responseObject.getString("location");
                    String company = responseObject.getString("company");
                    String blog = responseObject.getString("blog");
                    Integer followers = responseObject.getInt("followers");
                    Integer following = responseObject.getInt("following");
                    UserDetail userDetail;
                    userDetail = builder.setId(id).setName(name).setLocation(location).setCompany(company).setBlog(blog).build();

                    binding.detailName.setText(new StringBuilder("Name : ").append(userDetail.getName()));
                    binding.detailLocation.setText(new StringBuilder("Location : ").append(userDetail.getLocation()));
                    binding.detailCompany.setText(new StringBuilder("Company : ").append(userDetail.getCompany()));
                    binding.detailBlog.setText(new StringBuilder("Blog : ").append(userDetail.getBlog()));
                    binding.tabLayout.getTabAt(0).setText("FOLLOWERS (" + followers + ")");
                    binding.tabLayout.getTabAt(1).setText("FOLLOWING (" + following + ")");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
