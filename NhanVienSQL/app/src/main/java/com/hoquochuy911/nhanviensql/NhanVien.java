package com.hoquochuy911.nhanviensql;

public class NhanVien {
    private  String maNV;
    private String tenNV;
    private String img;
    private Enum.Gender gender;
    private PhongBan pb;

    public NhanVien() {
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String img, Enum.Gender gender, PhongBan pb) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.img = img;
        this.gender = gender;
        this.pb = pb;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Enum.Gender getGender() {
        return gender;
    }

    public void setGender(Enum.Gender gender) {
        this.gender = gender;
    }

    public PhongBan getPb() {
        return pb;
    }

    public void setPb(PhongBan pb) {
        this.pb = pb;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", tenNV='" + tenNV + '\'' +
                ", img='" + img + '\'' +
                ", gender=" + gender +
                ", pb=" + pb +
                '}';
    }
}
