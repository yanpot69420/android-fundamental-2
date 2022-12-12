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
import com.example.submission2fundamental.model.User;
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
                        String followers = object.getString("followers_url");
                        String following = object.getString("following_url");
                        following = following.substring(0 , following.length()-13);
                        User user;
                        user = new User(avatar, id, username, followers, following);
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
}
