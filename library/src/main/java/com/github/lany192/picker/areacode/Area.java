package com.github.lany192.picker.areacode;

import androidx.annotation.NonNull;

public class Area implements PyEntity {
    public int code;
    public String name, locale, pinyin;
    private boolean hot;

    public Area(boolean hot, int code, String name, String pinyin, String locale) {
        this.hot = hot;
        this.code = code;
        this.name = name;
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
                ", hot=" + hot +
                '}';
    }

    public String toJson() {
        return "{\"name\":\"" + name + "\", \"code\":" + code + ",\"pinyin\":" + pinyin + ",\"locale\":\"" + locale + "\"}";
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
