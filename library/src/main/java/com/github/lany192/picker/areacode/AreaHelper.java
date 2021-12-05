package com.github.lany192.picker.areacode;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AreaHelper {
    private volatile static AreaHelper instance = null;
    private List<Area> countries = new ArrayList<>();

    private AreaHelper() {
    }

    public static AreaHelper get() {
        if (instance == null) {
            synchronized (AreaHelper.class) {
                if (instance == null) {
                    instance = new AreaHelper();
                }
            }
        }
        return instance;
    }

    public List<Area> getAll() {
        return new ArrayList<>(countries);
    }

    public Area fromJson(String json) {
        if (TextUtils.isEmpty(json)) return null;
        try {
            JSONObject jo = new JSONObject(json);
            return new Area(jo.optBoolean("hot"), jo.optInt("code"), jo.optString("name"), jo.optString("pinyin"), jo.optString("locale"), jo.optInt("flag"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void load(@NonNull Context ctx, Language language) throws IOException, JSONException {
        countries = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(ctx.getResources().getAssets().open("area-code.json")));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
            sb.append(line);
        br.close();
        JSONArray ja = new JSONArray(sb.toString());
        String key = language.key;
        for (int i = 0; i < ja.length(); i++) {
            JSONObject jo = ja.getJSONObject(i);
            int flag = 0;
            String locale = jo.getString("locale");
            if (!TextUtils.isEmpty(locale)) {
                flag = ctx.getResources().getIdentifier("flag_" + locale.toLowerCase(), "drawable", ctx.getPackageName());
            }
            String name = jo.getString(key);
            countries.add(new Area(jo.optBoolean("hot"),
                    jo.getInt("code"),
                    name,
                    language == Language.ENGLISH ? name : jo.getString("pinyin"),
                    locale, flag)
            );
        }
    }

    public void destroy() {
        countries.clear();
    }
}
