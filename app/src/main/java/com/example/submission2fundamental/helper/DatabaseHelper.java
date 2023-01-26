package com.example.submission2fundamental.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.submission2fundamental.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "githubuser.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_favorites = "CREATE TABLE \"favorites\" (\n" +
                "\t\"user_id\" VARCHAR PRIMARY KEY,\n" +
                "\t\"user_name\" VARCHAR NOT NULL\t,\n" +
                "\t\"user_avatar\" VARCHAR NOT NULL , \n" +
                "\t\"user_type\" VARCHAR NOT NULL,\n" +
                "\t\"user_url\" VARCHAR NOT NULL\n" +
                ");";

        db.execSQL(create_favorites);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_favorites = "DROP TABLE IF EXISTS favorites";
        db.execSQL(drop_favorites);
    }

    public Boolean addFavorites(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", user.getId());
        cv.put("user_name", user.getUsername());
        cv.put("user_avatar", user.getAvatar());
        cv.put("user_type", user.getType());
        cv.put("user_url", user.getUrl());
        long insert  = db.insert("favorites", null, cv);
        return insert != -1;
    }

    public Boolean removeFavorites(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM favorites WHERE user_id = "+id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
            return false;
        cursor.close();
        return true;
    }

    public Boolean getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM favorites WHERE user_id = "+id;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst())
            return true;
        cursor.close();
        return false;
    }

    public List<User> getFavoriteUsers(){
        ArrayList<User> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM favorites";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String avatar = cursor.getString(2);
                String type = cursor.getString(3);
                String url = cursor.getString(4);
                User user = new User(avatar, id, name, type, url);
                returnList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
