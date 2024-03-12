package com.hoquochuy911.nhanviensql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {

    public DataHelper(@Nullable Context context) {
        super(context, "NhanVien.sqLite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PHONGBAN_TABLE = "CREATE TABLE PhongBan(" +
                "maPB TEXT PRIMARY KEY, " +
                "tenPB TEXT )";
        db.execSQL(CREATE_PHONGBAN_TABLE);

        String CREATE_NHANVIEN_TABLE = "CREATE TABLE NhanVien ( " +
                "maNV TEXT PRIMARY KEY, " +
                "tenNV TEXT, " +
                "img TEXT, " +
                "gender INTEGER, " +
                "pb TEXT, " +
                "FOREIGN KEY(pb) REFERENCES PhongBan(maPB) )";
        db.execSQL(CREATE_NHANVIEN_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS PhongBan");
        onCreate(db);
    }

    //xử lý nhân viên

    public int insertNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNV", nhanVien.getMaNV());
        contentValues.put("tenNV", nhanVien.getTenNV());
        contentValues.put("img", nhanVien.getImg());
        contentValues.put("gender", nhanVien.getGender().ordinal()); // 0 for 'Nam', 1 for 'Nữ'
        contentValues.put("pb", nhanVien.getPb().getMaPB());
        int result = (int) db.insert("NhanVien", null, contentValues);
        db.close();
        return result;
    }
    public ArrayList<NhanVien> getAllNhanVien() {
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NhanVien", null);
        if (cursor.moveToFirst()) {
            do {
                String maNV = cursor.getString(0);
                String tenNV = cursor.getString(1);
                String img = cursor.getString(2);
                Enum.Gender gender = Enum.Gender.values()[cursor.getInt(3)];

                String maPB = cursor.getString(4);
                PhongBan pb = getPhongBan(maPB);

                NhanVien nhanVien = new NhanVien(maNV, tenNV, img, gender, pb);
                nhanVienList.add(nhanVien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nhanVienList;
    }
    public int updateNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maNV", nhanVien.getMaNV());
        contentValues.put("tenNV", nhanVien.getTenNV());
        contentValues.put("img", nhanVien.getImg());
        contentValues.put("gender", nhanVien.getGender().ordinal()); // 0 for 'Nam', 1 for 'Nữ'
        contentValues.put("pb", nhanVien.getPb().getMaPB());
        int result = db.update("NhanVien", contentValues, "maNV = ?", new String[]{nhanVien.getMaNV()});
        db.close();
        return result;
    }
    public int deleteNhanVien(String maNV) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("NhanVien", "maNV = ?", new String[]{maNV});
        db.close();
        return result;
    }

    // xử lý phòng ban

    public ArrayList<PhongBan> getAllPhongBan() {
        ArrayList<PhongBan> phongBanList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhongBan", null);
        if (cursor.moveToFirst()) {
            do {
                String maPB = cursor.getString(0);
                String tenPB = cursor.getString(1);
                PhongBan phongBan = new PhongBan(maPB, tenPB);
                phongBanList.add(phongBan);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return phongBanList;
    }
    public PhongBan getPhongBan(String maPB) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PhongBan WHERE maPB = ?", new String[]{maPB});
        if (cursor != null)
            cursor.moveToFirst();

        String tenPB = cursor.getString(1);
        PhongBan phongBan = new PhongBan(maPB, tenPB);
        cursor.close();
        return phongBan;
    }
    public int updatePhongBan(PhongBan phongBan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maPB", phongBan.getMaPB());
        contentValues.put("tenPB", phongBan.getTenPB());
        int result = db.update("PhongBan", contentValues, "maPB = ?", new String[]{String.valueOf(phongBan.getMaPB())});
        db.close();
        return result;
    }
    public int deletePhongBan(String maPB) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("PhongBan", "maPB = ?", new String[]{String.valueOf(maPB)});
        db.close();
        return result;
    }
    public int insertPhongBan(PhongBan phongBan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maPB", phongBan.getMaPB());
        contentValues.put("tenPB", phongBan.getTenPB());
        int result = (int) db.insert("PhongBan", null, contentValues);
        db.close();
        return result;
    }
}
