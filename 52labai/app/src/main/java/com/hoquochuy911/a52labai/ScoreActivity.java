package com.hoquochuy911.a52labai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView tvDiemNguoi = findViewById(R.id.tvDiemNguoi);
        TextView tvKQ = findViewById(R.id.tvKQ);
        TextView tvDiemMay = findViewById(R.id.tvDiemMay);
        Button btnChoiLai = findViewById(R.id.btChoiLai);
        LinearLayout layout = findViewById(R.id.layoutLi);

        int diemN = Integer.parseInt(getIntent().getExtras().getString("DiemMay1"));
        int diemM = Integer.parseInt(getIntent().getExtras().getString("DiemMay2"));

        if(diemN > diemM){
            tvKQ.setText("Máy 1 đã thắng !!!");
            tvDiemNguoi.setText("Điểm của máy 1 là: "+diemN);
            tvDiemMay.setText("Điểm của máy 2 là: " + diemM);
        }else if(diemN < diemM){
            tvKQ.setText("Máy 2 đã thắng !!!");
            tvDiemNguoi.setText("Điểm của máy 1 là: "+diemN);
            tvDiemMay.setText("Điểm của máy 2 là: " + diemM);
        }else{
            tvKQ.setText("Hòa !!!");
            tvDiemNguoi.setText("Điểm của máy 1 là: "+diemN);
            tvDiemMay.setText("Điểm của máy 2 là: " + diemM);
        }

        btnChoiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
