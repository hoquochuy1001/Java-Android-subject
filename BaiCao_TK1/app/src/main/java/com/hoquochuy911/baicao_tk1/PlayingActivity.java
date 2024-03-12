package com.hoquochuy911.baicao_tk1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayingActivity extends AppCompatActivity {

    private TextView tvBoDem;
    private int SoGiay;

    private int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};

    private ImageView imv1, imv2, imv3, imv4, imv5, imv6;
    private TextView tvDiem_May1, tvKetqua_May1, tvDiem_May2, tvKetQua_May2, tvVan;

    int diemMay1 = 0, diemMay2 = 0;
    int van = 0;

    int luot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        tvBoDem = findViewById(R.id.tvBoDem);
        SoGiay = getIntent().getExtras().getInt("SoPhut");
        SoGiay = SoGiay * 60 * 1000;

        imv1 = findViewById(R.id.imageView1);
        imv2 = findViewById(R.id.imageView2);
        imv3 = findViewById(R.id.imageView3);
        imv4 = findViewById(R.id.imageView4);
        imv5 = findViewById(R.id.imageView5);
        imv6 = findViewById(R.id.imageView6);

        tvDiem_May2 = findViewById(R.id.tv_Diem_May2);
        tvKetQua_May2 = findViewById(R.id.tv_KetQua_May2);
        tvDiem_May1 = findViewById(R.id.tv_Diem_May1);
        tvKetqua_May1 = findViewById(R.id.tv_KetQua_May1);

        tvVan = findViewById(R.id.tv_Van);

        CountDownTimer timer = new CountDownTimer(SoGiay, 1000) {

            int[] saulabai;
            int[] baso_May1;
            int[] baso_May2;

            int diemMay1 = 0, diemMay2 = 0;

            @Override
            public void onTick(long l) {

                tvBoDem.setText("Thời gian còn lại là: " + l/1000 + " giây");

                luot++;
                if(luot == 1){
                    van ++;
                    tvVan.setText("Ván: " + van);
                    saulabai = laySauSoNgauNhien(0,51);
                    baso_May1 = new int[3];
                    baso_May2 = new int[3];

                    for (int i = 0; i < 3; i++)
                        baso_May1[i] = saulabai[i];
                    for (int i = 0; i < 3; i++)
                        baso_May2[i] = saulabai[i+3];

                    imv1.setImageResource(0);
                    imv2.setImageResource(0);
                    imv3.setImageResource(0);
                    imv4.setImageResource(0);
                    imv5.setImageResource(0);
                    imv6.setImageResource(0);

                    tvKetqua_May1.setText("");
                    tvKetQua_May2.setText("");
                }
                if(luot == 2){
                    imv1.setImageResource(manghinhbai[baso_May1[0]]);
                    imv2.setImageResource(manghinhbai[baso_May1[1]]);
                    imv3.setImageResource(manghinhbai[baso_May1[2]]);
                    tvKetqua_May1.setText(tinhKetQua(baso_May1));
                    diemMay1 = tinhSoNut(baso_May1);
                }
                if(luot == 3){
                    imv4.setImageResource(manghinhbai[baso_May2[0]]);
                    imv5.setImageResource(manghinhbai[baso_May2[1]]);
                    imv6.setImageResource(manghinhbai[baso_May2[2]]);
                    tvKetQua_May2.setText(tinhKetQua(baso_May2));
                    diemMay2 = tinhSoNut(baso_May2);
                    tinhDiem(diemMay1, diemMay2);
                    luot = 0;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(PlayingActivity.this, ScoreActivity.class);
                intent.putExtra("DiemMay1", tvDiem_May1.getText().toString().trim().substring(6));
                intent.putExtra("DiemMay2", tvDiem_May2.getText().toString().trim().substring(6));
                startActivityForResult(intent, 999);
            }
        };
        timer.start();
    }

    private int[] laySauSoNgauNhien(int min, int max){
        int[] baso = new int[6];
        int i = 0;
        baso[i++] = random(min, max);
        do{
            int k = random(min, max);
            if(!kiemTraTrung(k, baso))
                baso[i++] = k;
        }while(i<6);
        return baso;
    }
    //---------------------------------------------------------------------
    private boolean kiemTraTrung(int k, int[]arr){
        for(int i = 0; i < arr.length; i++)
            if(arr[i] == k)
                return true;
        return false;
    }
    //---------------------------------------------------------------------
    private int random(int min, int max){
        return min + (int)(Math.random()*((max-min) + 1));
    }
    //---------------------------------------------------------------------
    private  int tinhSoTay(int[]arr){
        int k = 0;
        for(int i = 0; i < arr.length; i++)
            if(arr[i] % 13 >=10 && arr[i] >= 10)
                k++;
        return k;
    }
    //---------------------------------------------------------------------
    private String tinhKetQua(int[]arr){
        String ketqua = "";
        if(tinhSoTay(arr) == 3)
            ketqua = "3 tây";
        else{
            int tong = 0;
            for(int i =0; i< arr.length; i++)
                if(arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;
            if(tong % 10 == 0)
                ketqua = "bù, số tây là " + tinhSoTay(arr);
            else
                ketqua = (tong%10) + " nút, số tây là " + tinhSoTay(arr);

        }
        return ketqua;
    }

    //-------------------------------------------------------------------
    private int tinhSoNut(int[]arr){
        int soNut = 0;
        if(tinhSoTay(arr) == 3)
            soNut = 10;
        else{
            int tong = 0;
            for(int i =0; i< arr.length; i++)
                if(arr[i] % 13 < 10)
                    tong += arr[i] % 13 + 1;
            if(tong % 10 == 0)
                soNut = 0;
            else
                soNut = (tong%10);
        }
        return soNut;
    }
    private void tinhDiem(int may1, int may2) {
        if (may1 > may2){
            Toast toast = Toast.makeText(this,"Máy 1 đã thắng !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
            diemMay1++;
            tvDiem_May1.setText("Điểm: " + diemMay1);
        }else if(may1 < may2){
            Toast toast = Toast.makeText(this,"Máy 2 đã thắng !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
            diemMay2++;
            tvDiem_May2.setText("Điểm: " + diemMay2);
        }else{
            Toast toast = Toast.makeText(this,"Hòa !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){

            van = 0;
            diemMay1 = 0;
            diemMay2 = 0;

            tvVan.setText("Ván: ");
            tvDiem_May1.setText("Điểm: 0");
            tvDiem_May2.setText("Điểm: 0");

            tvKetqua_May1.setText("");
            tvKetQua_May2.setText("2");

            imv1.setImageResource(0);
            imv2.setImageResource(0);
            imv3.setImageResource(0);
            imv4.setImageResource(0);
            imv5.setImageResource(0);
            imv6.setImageResource(0);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}