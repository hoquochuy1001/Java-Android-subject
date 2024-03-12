package com.hoquochuy911.onthizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(@Nullable Context context) {
        super(context, "Sach.sqLite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TACGIA_TABLE = "CREATE TABLE TacGia(" +
                "maTacGia TEXT PRIMARY KEY," +
                "tenTacGia TEXT)";
        db.execSQL(CREATE_TACGIA_TABLE);

        String CREATE_SACH_TABLE = "CREATE TABLE Sach(" +
                "maSach TEXT PRIMARY KEY," +
                "tenSach TEXT," +
                "tg TEXT," +
                "FOREIGN KEY(tg) REFERENCES TacGia(maTacGia))";
        db.execSQL(CREATE_SACH_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists TacGia");
        db.execSQL("drop table if exists Sach");
        onCreate(db);
    }

    // tac gia

    public ArrayList<TacGia> getAllTacGia() {
        ArrayList<TacGia> tg_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TacGia", null);
        if (cursor.moveToFirst()) {
            do {
                String maTacGia = cursor.getString(0);
                String tenTacGia = cursor.getString(1);
                TacGia tacGia = new TacGia(maTacGia, tenTacGia);
                tg_list.add(tacGia);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tg_list;
    }

    public TacGia getTacGia(String maTG) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TacGia WHERE maTacGia = ?", new String[]{maTG});
        if (cursor != null)
            cursor.moveToFirst();

        String tenTG = cursor.getString(1);
        TacGia tg = new TacGia(maTG, tenTG);
        cursor.close();
        return tg;
    }

    public int insertTacGia(TacGia tacGia){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTacGia", tacGia.getMaTacGia());
        contentValues.put("tenTacGia", tacGia.getTenTacGia());
        int result = (int) db.insert("TacGia", null, contentValues);
        db.close();
        return result;
    }

    public int updateTacGia(TacGia tacGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTacGia", tacGia.getMaTacGia());
        contentValues.put("tenTacGia", tacGia.getTenTacGia());
        int result = db.update("TacGia", contentValues, "maTacGia = ?", new String[]{String.valueOf(tacGia.getMaTacGia())});
        db.close();
        return result;
    }
    public int deleteTacGia(String maTacGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("TacGia", "maTacGia = ?", new String[]{String.valueOf(maTacGia)});
        db.close();
        return result;
    }

    // sach

    public int insertSach(Sach sach){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSach", sach.getMaSach());
        contentValues.put("tenSach", sach.getTenSach());
        contentValues.put("tg", sach.getMatg().getMaTacGia());
        int result = (int) db.insert("Sach", null, contentValues);
        db.close();
        return result;
    }

    public ArrayList<Sach> getAllSach() {
        ArrayList<Sach> sachList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Sach", null);
        if (cursor.moveToFirst()) {
            do {
                String maSach = cursor.getString(0);
                String tenSach = cursor.getString(1);

                String maTacGia = cursor.getString(2);
                TacGia tacGia = getTacGia(maTacGia);

                Sach sach = new Sach(maSach, tenSach, tacGia);
                sachList.add(sach);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return sachList;
    }
}
