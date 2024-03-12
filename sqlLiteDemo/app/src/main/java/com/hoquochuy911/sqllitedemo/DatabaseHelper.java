package com.hoquochuy911.sqllitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "BD.sqLite",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Authors(" +
                "id_author integer primary key," +
                "name text," +
                "address text," +
                "email text)");
        db.execSQL("create table Books(" +
                "id integer primary key," +
                "title text," +
                "id_author integer not null constraint id_author references " +
                "Authors(id_author) on delete cascade on update cascade);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Authors");
        onCreate(db);
    }

    public int insertAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_author", author.getId_author());
        contentValues.put("name", author.getName());
        contentValues.put("address", author.getAddress());
        contentValues.put("email", author.getEmail());
        int result = (int) db.insert("Authors", null, contentValues);
        db.close();
        return result;
    }
    public int updateAuthor(Author author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", author.getName());
        contentValues.put("address", author.getAddress());
        contentValues.put("email", author.getEmail());
        int result = db.update("Authors", contentValues, "id_author = ?", new String[]{String.valueOf(author.getId_author())});
        db.close();
        return result;
    }
    public ArrayList<Author> getAllAuthor(){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors", null);

        if(cursor != null)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            list.add(new Author(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<Author> getIdAuthor(int id_author){
        ArrayList<Author> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Authors where id_author = " + id_author, null);

        if(cursor != null)
            cursor.moveToFirst();
        Author author = new Author(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        list.add(author);
        cursor.close();
        db.close();
        return list;
    }
    public int deleteAuthor(int id_author) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Authors", "id_author = ?", new String[]{String.valueOf(id_author)});
        db.close();
        return result;
    }
    public int insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", book.getId());
        contentValues.put("title", book.getTitle());
        contentValues.put("id_author", book.getId_author().getId_author());
        int result = (int) db.insert("Books", null, contentValues);
        db.close();
        return result;
    }

}
