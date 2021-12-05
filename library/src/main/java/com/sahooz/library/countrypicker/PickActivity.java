package com.sahooz.library.countrypicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PickActivity extends AppCompatActivity {
    private List<Area> selectedCountries = new ArrayList<>();
    private List<Area> allCountries = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        RecyclerView rvPick = findViewById(R.id.rv_pick);
        SideBar side = findViewById(R.id.side);
        EditText etSearch = findViewById(R.id.et_search);
        TextView tvLetter = findViewById(R.id.tv_letter);
        allCountries.clear();
        allCountries.addAll(AreaHelper.get().getAll());
        selectedCountries.clear();
        selectedCountries.addAll(allCountries);
        final AreaAdapter adapter = new AreaAdapter(selectedCountries);
        adapter.setOnSelectListener(area -> {
            Intent data = new Intent();
            data.putExtra("country", area.toJson());
            setResult(Activity.RESULT_OK, data);
            finish();
        });
        rvPick.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvPick.setLayoutManager(manager);
        rvPick.setAdapter(adapter);
        rvPick.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                selectedCountries.clear();
                for (Area country : allCountries) {
                    if (country.name.toLowerCase().contains(string.toLowerCase()))
                        selectedCountries.add(country);
                }
                adapter.update(selectedCountries);
            }
        });
        side.addIndex("#", side.indexes.size());
        side.setOnLetterChangeListener(new SideBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                tvLetter.setVisibility(View.VISIBLE);
                tvLetter.setText(letter);
                int position = adapter.getLetterPosition(letter);
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }

            @Override
            public void onReset() {
                tvLetter.setVisibility(View.GONE);
            }
        });
    }
}
