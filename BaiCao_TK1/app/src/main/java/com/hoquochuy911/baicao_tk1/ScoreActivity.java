package com.hoquochuy911.baicao_tk1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView tvKQ = findViewById(R.id.tvKQ);
        Button btnChoiLai = findViewById(R.id.btChoiLai);
        LinearLayout layout = findViewById(R.id.layoutLi);

        int diemN = Integer.parseInt(getIntent().getExtras().getString("DiemMay1"));
        int diemM = Integer.parseInt(getIntent().getExtras().getString("DiemMay2"));

        if(diemN > diemM){
            tvKQ.setText("Máy 1 đã thắng !!!");
        }else if(diemN < diemM){
            tvKQ.setText("Máy 2 đã thắng !!!");
        }else{
            tvKQ.setText("Hòa !!!");
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