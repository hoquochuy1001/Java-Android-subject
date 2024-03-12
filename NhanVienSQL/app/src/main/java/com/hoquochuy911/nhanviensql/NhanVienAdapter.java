package com.hoquochuy911.nhanviensql;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienViewHolder> {
    private ArrayList<NhanVien> nhanViens;
    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public NhanVienAdapter(NhanVienActivity nhanVienActivity, ArrayList<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }
    @Override
    public NhanVienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_vien, parent, false);
        return new NhanVienViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(NhanVienViewHolder holder, int position) {
        NhanVien nhanVien = nhanViens.get(position);
        if (nhanVien.getImg() != null && !nhanVien.getImg().isEmpty()) {
            Glide.with(holder.imgView.getContext())
                    .load(nhanVien.getImg())
                    .into(holder.imgView);
        } else {
            holder.imgView.setImageResource(R.drawable.profile); // Ví dụ
        }
        holder.tvMaNV.setText(nhanVien.getMaNV());
        holder.tvTenNV.setText(nhanVien.getTenNV());
        holder.tvGender.setText(nhanVien.getGender().toString());
        holder.tvTenPB.setText(nhanVien.getPb().getTenPB());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mListener != null && position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return nhanViens.size();
    }
}
