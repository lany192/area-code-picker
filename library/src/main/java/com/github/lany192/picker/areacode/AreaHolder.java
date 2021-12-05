package com.github.lany192.picker.areacode;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class AreaHolder extends RecyclerView.ViewHolder {

    TextView tvName;
    TextView tvCode;
    ImageView ivFlag;

    AreaHolder(View itemView) {
        super(itemView);
        ivFlag = itemView.findViewById(R.id.iv_flag);
        tvName = itemView.findViewById(R.id.tv_name);
        tvCode = itemView.findViewById(R.id.tv_code);
    }
}
