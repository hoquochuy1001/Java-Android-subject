package com.hoquochuy911.nhanviensql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangKyActivity extends AppCompatActivity {

    private Button btnQuayLai;
    private SharedPreferences sharedPreferences;
    private static final  String pre_Login = "DangKy";
    private static final String key_User = "TaiKhoan";
    private static final String key_Pass= "MatKhau";
    private EditText txtDKTaiKhoan;
    private EditText txtDKMatKhau;
    private Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        sharedPreferences = getSharedPreferences(pre_Login,MODE_PRIVATE);
        txtDKTaiKhoan = findViewById(R.id.txtDKDangNhap);
        txtDKMatKhau = findViewById(R.id.txtDKMatKhau);

        btnDangKy = findViewById(R.id.btnDangky);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(key_User,txtDKTaiKhoan.getText().toString());
                edit.putString(key_Pass,txtDKMatKhau.getText().toString());
                edit.apply();

                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(i);
            }
        });


        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnQuayLai .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(i);
            }
        });

    }
}