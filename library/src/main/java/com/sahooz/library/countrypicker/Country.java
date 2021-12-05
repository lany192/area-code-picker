package com.sahooz.library.countrypicker;

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


public class Country implements PyEntity {
    private static List<Country> countries = new ArrayList<>();
    public int code;
    public String name, locale, pinyin;
    public int flag;

    public Country(int code, String name, String pinyin, String locale, int flag) {
        this.code = code;
        this.name = name;
        this.flag = flag;
        this.locale = locale;
        this.pinyin = pinyin;
    }

    public static List<Country> getAll() {
        return new ArrayList<>(countries);
    }

    public static Country fromJson(String json) {
        if (TextUtils.isEmpty(json)) return null;
        try {
            JSONObject jo = new JSONObject(json);
            return new Country(jo.optInt("code"), jo.optString("name"), jo.optString("pinyin"), jo.optString("locale"), jo.optInt("flag"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void load(@NonNull Context ctx, Language language) throws IOException, JSONException {
        countries = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(ctx.getResources().getAssets().open("code.json")));
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
            countries.add(new Country(
                    jo.getInt("code"),
                    name,
                    language == Language.ENGLISH ? name : jo.getString("pinyin"),
                    locale, flag)
            );
        }
    }

    public static void destroy() {
        countries.clear();
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                "flag='" + flag + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String toJson() {
        return "{\"name\":\"" + name + "\", \"code\":" + code + ", \"flag\":" + flag + ", \"pinyin\":" + pinyin + ",\"locale\":\"" + locale + "\"}";
    }

    @Override
    public int hashCode() {
        return code;
    }

    @NonNull
    @Override
    public String getPinyin() {
        return pinyin;
    }
}
