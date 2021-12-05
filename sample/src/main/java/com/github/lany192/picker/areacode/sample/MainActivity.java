package com.github.lany192.picker.areacode.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.lany192.picker.areacode.Area;
import com.github.lany192.picker.areacode.AreaHelper;
import com.github.lany192.picker.areacode.Language;
import com.github.lany192.picker.areacode.PickActivity;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            AreaHelper.get().load(this, Language.SIMPLIFIED_CHINESE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void click(View view) {
        startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            Area country = AreaHelper.get().fromJson(data.getStringExtra("country"));
            Toast.makeText(this, country.toJson(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        AreaHelper.get().destroy();
        super.onDestroy();
    }
}
