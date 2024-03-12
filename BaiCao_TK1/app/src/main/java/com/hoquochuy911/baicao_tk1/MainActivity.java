package com.hoquochuy911.baicao_tk1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etSoPhut;
    private Button btnBatDau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSoPhut = findViewById(R.id.etSoPhut);
        btnBatDau = findViewById(R.id.btBatDau);

        btnBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = etSoPhut.getText().toString().trim();
                int SoPhut;
                try {
                    if (str.trim().equals("")) {
                        Toast.makeText(MainActivity.this, "Bạn chưa nhập số phút !!!", Toast.LENGTH_SHORT).show();
                        etSoPhut.requestFocus();
                    }else {
                        SoPhut = Integer.parseInt(str);
                        Intent intent = new Intent(MainActivity.this, PlayingActivity.class);
                        intent.putExtra("SoPhut", SoPhut);
                        startActivityForResult(intent, 888);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Số phút không hợp lệ !!!", Toast.LENGTH_SHORT).show();
                    etSoPhut.requestFocus();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 888 && resultCode == RESULT_OK){
            etSoPhut.setText("");
            etSoPhut.requestFocus();
        }
    }
}