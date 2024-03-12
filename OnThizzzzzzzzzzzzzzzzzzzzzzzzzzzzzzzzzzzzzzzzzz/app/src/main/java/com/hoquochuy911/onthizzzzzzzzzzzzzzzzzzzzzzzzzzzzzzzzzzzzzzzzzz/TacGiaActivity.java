package com.hoquochuy911.onthizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class TacGiaActivity extends AppCompatActivity {
    EditText edt_maTG, edt_tenTG;
    Button btn_xem, btn_them, btn_xoa, btn_sua, btn_thoat;
    GridView gridView;
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tac_gia);

        edt_maTG = findViewById(R.id.edtMaTacGia);
        edt_tenTG = findViewById(R.id.edtTenTacGia);

        btn_them = findViewById(R.id.btnThem);
        btn_xem = findViewById(R.id.btnXem);
        btn_xoa = findViewById(R.id.btnXoa);
        btn_sua = findViewById(R.id.btnSua);
        btn_thoat = findViewById(R.id.btnThoat);

        gridView = findViewById(R.id.GridView_display);

        dataHelper = new DataHelper(this);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TacGia tg = new TacGia();
                tg.setMaTacGia(edt_maTG.getText().toString());
                tg.setTenTacGia(edt_tenTG.getText().toString());
                dataHelper.insertTacGia(tg);
            }
        });

        btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<TacGia> list_tg = dataHelper.getAllTacGia();
                ArrayList<String> list_string = new ArrayList<>();
                for(TacGia tacGia : list_tg){
                    list_string.add(tacGia.getMaTacGia());
                    list_string.add(tacGia.getTenTacGia());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(TacGiaActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TacGia selectTG = list_tg.get(position/2);
                        edt_maTG.setText(selectTG.getMaTacGia());
                        edt_tenTG.setText(selectTG.getTenTacGia());
                    }
                });
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TacGia tacGia = new TacGia();
                tacGia.setMaTacGia(edt_maTG.getText().toString());
                tacGia.setTenTacGia(edt_tenTG.getText().toString());
                dataHelper.updateTacGia(tacGia);
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_maTG.getText().toString();
                dataHelper.deleteTacGia(id);
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}