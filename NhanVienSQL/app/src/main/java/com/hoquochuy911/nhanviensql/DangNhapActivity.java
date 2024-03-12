package com.hoquochuy911.nhanviensql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DangNhapActivity extends AppCompatActivity {

    private TextView txtDangKyTaiKhoan;
    private EditText txtNguoiDung;
    private EditText txtTaiKhoan;
    private Button btnDangNhap;
    private SharedPreferences sharedPreferences;
    private static final  String pre_Login = "DangKy";
    private static final String key_User = "TaiKhoan";
    private static final String key_Pass= "MatKhau";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        txtNguoiDung = findViewById(R.id.txtDangNhap);
        txtTaiKhoan = findViewById(R.id.txtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        sharedPreferences = getSharedPreferences(pre_Login,MODE_PRIVATE);

        String user = sharedPreferences.getString(key_User,null);
        String pass =sharedPreferences.getString(key_Pass,null);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNhap = txtNguoiDung.getText().toString();
                String passNhap = txtTaiKhoan.getText().toString();

                if (!userNhap.equals(user)){
                    Toast.makeText(DangNhapActivity.this, "Sai tài khoản", Toast.LENGTH_SHORT).show();
                } else if (!passNhap.equals(pass)){
                    Toast.makeText(DangNhapActivity.this, "Sai Mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });

        txtDangKyTaiKhoan = findViewById(R.id.txtDangKyTaiKhoan);
        txtDangKyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtDangKyTaiKhoan.setTextColor(Color.BLUE);
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }
        });

    }
}