package com.github.lany192.picker.areacode;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class AreaHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    TextView tvCode;

    AreaHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvCode = itemView.findViewById(R.id.tv_code);
    }
}
