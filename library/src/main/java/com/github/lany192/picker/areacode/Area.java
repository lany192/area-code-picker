package com.github.lany192.picker.areacode;

import androidx.annotation.NonNull;

public class Area implements PyEntity {
    public int code;
    public String name, locale, pinyin;
    private int flag;
    private boolean hot;

    public Area(boolean hot, int code, String name, String pinyin, String locale, int flag) {
        this.hot = hot;
        this.code = code;
        this.name = name;
        this.flag = flag;
        this.locale = locale;
        this.pinyin = pinyin;
    }


    @Override
    public String toString() {
        return "Area{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", locale='" + locale + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", flag=" + flag +
                ", hot=" + hot +
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
