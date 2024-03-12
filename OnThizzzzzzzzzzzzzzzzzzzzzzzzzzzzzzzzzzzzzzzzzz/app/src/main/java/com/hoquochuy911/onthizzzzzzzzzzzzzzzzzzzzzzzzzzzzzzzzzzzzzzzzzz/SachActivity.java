package com.hoquochuy911.onthizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SachActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    String selectedImagePath;
    ImageView imageView;
    EditText edt_maSach, edt_tenSach;
    Button btn_img, btn_xem, btn_them, btn_thoat, btn_xoa, btn_sua;
    Spinner spinner;
    GridView gridView;
    DataHelper dataHelper;
    TacGia selectedTacGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);

        imageView = findViewById(R.id.imageView);
        dataHelper = new DataHelper(this);

        edt_maSach = findViewById(R.id.edtMaSach);
        edt_tenSach = findViewById(R.id.edtTenSach);

        btn_img = findViewById(R.id.btnThemAnh);
        btn_them = findViewById(R.id.btn_Them);
        btn_xem = findViewById(R.id.btn_Xem);
        btn_xoa = findViewById(R.id.btn_Xoa);
        btn_thoat = findViewById(R.id.btnThoat);
        btn_sua = findViewById(R.id.btn_Sua);

        gridView = findViewById(R.id.GridView_display);

        spinner = findViewById(R.id.spinner);

        dataHelper = new DataHelper(this);

        ArrayList<TacGia> list_tg = dataHelper.getAllTacGia();
        ArrayList<String> list_string = new ArrayList<>();
        for (TacGia tacGia : list_tg) {
            list_string.add(tacGia.getMaTacGia());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_string);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMaTG = (String) parent.getItemAtPosition(position);
                selectedTacGia = dataHelper.getTacGia(selectedMaTG);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTacGia = null;
            }
        });

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach sach = new Sach();
                sach.setMaSach(edt_maSach.getText().toString());
                sach.setTenSach(edt_tenSach.getText().toString());
                sach.setMatg(selectedTacGia);
                dataHelper.insertSach(sach);
            }
        });

        btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Sach> list_sach = dataHelper.getAllSach();
                ArrayList<String> list_string = new ArrayList<>();
                for(Sach sach : list_sach){
                    list_string.add(sach.getMaSach());
                    list_string.add(sach.getTenSach());
                    list_string.add(sach.getMatg().getTenTacGia());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SachActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sach selectedSach = list_sach.get(position/3);
                        edt_maSach.setText(selectedSach.getMaSach());
                        edt_tenSach.setText(selectedSach.getTenSach());
                        selectTacGiaInSpinner(selectedSach.getMatg());
                    }
                });
            }
        });
    }

    public void selectTacGiaInSpinner(TacGia tacGia) {
        int position = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(tacGia.getMaTacGia());
        spinner.setSelection(position);
    }


}