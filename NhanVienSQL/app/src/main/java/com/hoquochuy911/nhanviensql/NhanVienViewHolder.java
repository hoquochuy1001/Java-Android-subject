package com.hoquochuy911.nhanviensql;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class NhanVienViewHolder extends RecyclerView.ViewHolder {
    ImageView imgView;
    TextView tvMaNV, tvTenNV, tvGender, tvTenPB;
    public NhanVienViewHolder(View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imgview_img);
        tvMaNV = itemView.findViewById(R.id.tv_maNV);
        tvTenNV = itemView.findViewById(R.id.tv_tenNV);
        tvGender = itemView.findViewById(R.id.tv_gender);
        tvTenPB = itemView.findViewById(R.id.tv_tenPB);
    }
}