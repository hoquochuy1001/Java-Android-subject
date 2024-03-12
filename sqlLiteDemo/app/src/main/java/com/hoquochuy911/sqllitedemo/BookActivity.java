package com.hoquochuy911.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    EditText edt_id, edt_title;
    Spinner sp_idAuthor;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        edt_id = findViewById(R.id.edt_id);
        edt_title = findViewById(R.id.edt_title);

        sp_idAuthor = findViewById(R.id.sp_author);

        bt_save = findViewById(R.id.btn_save);
        bt_select = findViewById(R.id.btn_select);
        bt_exit = findViewById(R.id.button_exit);
        bt_update = findViewById(R.id.btn_update);
        bt_delete = findViewById(R.id.btn_delete);

        gv_display = findViewById(R.id.gridview_display);
        databaseHelper = new DatabaseHelper(this);

        int id_author = 0;
        ArrayList<Author> authors = databaseHelper.getIdAuthor(id_author);
        ArrayList<Integer> authorIds = new ArrayList<>();
        for (Author author : authors) {
            authorIds.add(author.getId_author());
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, authorIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_idAuthor.setAdapter(adapter);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}