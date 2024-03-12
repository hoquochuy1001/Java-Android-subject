package com.hoquochuy911.onthizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;

public class Sach {
    private String maSach;
    private String tenSach;
    private TacGia matg;
    public Sach() {
    }
    public Sach(String maSach, String tenSach, TacGia matg) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.matg = matg;
    }
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public TacGia getMatg() {
        return matg;
    }

    public void setMatg(TacGia matg) {
        this.matg = matg;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", matg=" + matg +
                '}';
    }
}
