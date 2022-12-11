package com.example.submission2fundamental.helper;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.submission2fundamental.MainActivity;
import com.example.submission2fundamental.adapter.UserAdapter;
import com.example.submission2fundamental.model.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class SyncHelper {

    public static void getUserList(Context context, String url, RecyclerView rvList, ProgressBar progressBar) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "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");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);
                ArrayList<User> list = new ArrayList<>();
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
//                    Toast.makeText(context, "Ini Result: " + result, Toast.LENGTH_LONG).show();
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
                        list.add(user);
                    }

                    UserAdapter adapter = new UserAdapter(list, context);
                    rvList.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.wtf("TAG", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
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
    }

    public static Integer countFollow(String url) {
        String result = "0";
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("User-Agent", "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");
//        client.addHeader("Authorization", "token <Personal Access Token>");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.wtf("What the heck", "Success coyy");
                String result = new String(responseBody);
                try {
                    JSONArray responseArray = new JSONArray(result);
                    result = String.valueOf(responseArray.length());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.wtf("Error to Count Follow", error.getMessage());
            }
        });
        return Integer.valueOf(result);
    }
}
