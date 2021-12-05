package com.sahooz.library.countrypicker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AreaAdapter extends PyAdapter<RecyclerView.ViewHolder> {
    private OnSelectListener listener;

    public AreaAdapter(List<? extends PyEntity> entities) {
        super(entities, '#');
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }

    @Override
    public LetterHolder onCreateLetterHolder(ViewGroup parent, int viewType) {
        return new LetterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter, parent, false));
    }

    @Override
    public AreaHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new AreaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_large_padding, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, PyEntity entity, int position) {
        AreaHolder areaHolder = (AreaHolder) holder;
        final Area country = (Area) entity;
        areaHolder.ivFlag.setImageResource(country.flag);
        areaHolder.tvName.setText(country.name + "(" + country.locale + ")");
        areaHolder.tvCode.setText("+" + country.code);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSelected(country);
            }
        });
    }

    @Override
    public void onBindLetterHolder(RecyclerView.ViewHolder holder, LetterEntity entity, int position) {
        ((LetterHolder) holder).textView.setText(entity.letter.toUpperCase());
    }

    public interface OnSelectListener {
        void onSelected(Area area);
    }
}