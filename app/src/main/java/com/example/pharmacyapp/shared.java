package com.example.pharmacyapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class shared {
    public static void writelist(Context context, ArrayList<Integer> list){
        Gson gson = new Gson();
        String jsonstring = gson.toJson(list);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
       editor.putString("key",jsonstring);
       editor.apply();
    }
    public static ArrayList<Integer>readlist(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonstring = pref.getString("key","");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
        ArrayList<Integer> list = gson.fromJson(jsonstring,type);
        return list;

    }
}
