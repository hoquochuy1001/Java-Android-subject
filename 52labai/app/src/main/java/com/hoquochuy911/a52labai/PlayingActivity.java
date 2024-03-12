package com.hoquochuy911.a52labai;

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
    int SoGiay;
    int diemMay1 = 0, diemMay2 = 0;
    int van = 0;
    int luot = 0;
    int manghinhbai[] = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6
            , R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10
            , R.drawable.cj, R.drawable.cq, R.drawable.ck

            , R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6
            , R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10
            , R.drawable.dj, R.drawable.dq, R.drawable.dk

            , R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5, R.drawable.h6
            , R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10
            , R.drawable.hj, R.drawable.hq, R.drawable.hk

            , R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6
            , R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10
            , R.drawable.sj, R.drawable.sq, R.drawable.sk
    };

    ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    TextView tv_ketQuaMay1, tv_ketQuaMay2,tv_diemMay1,tv_diemMay2, tv_Van, tv_BoDem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        SoGiay = getIntent().getExtras().getInt("SoPhut");
        SoGiay = SoGiay * 60 * 1000;

        iv1 = findViewById(R.id.imageView1);
        iv2 = findViewById(R.id.imageView2);
        iv3 = findViewById(R.id.imageView3);
        iv4 = findViewById(R.id.imageView4);
        iv5 = findViewById(R.id.imageView5);
        iv6 = findViewById(R.id.imageView6);

        tv_ketQuaMay1 = findViewById(R.id.tv_ketQuaMay1);
        tv_ketQuaMay2 = findViewById(R.id.tv_ketQuaMay2);
        tv_diemMay1 = findViewById(R.id.tv_diemMay1);
        tv_diemMay2 = findViewById(R.id.tv_diemMay2);

        tv_Van = findViewById(R.id.tv_Van);

        tv_BoDem = findViewById(R.id.tvBoDem);

//        btChon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int value[] = new int[3];
//                value = layBaSoNgauNhien(0, 51);
//
//                iv1.setImageResource(manghinhbai[value[0]]);
//                iv2.setImageResource(manghinhbai[value[1]]);
//                iv3.setImageResource(manghinhbai[value[2]]);
//
//                tv_ketQuaMay1.setText(tinhKetQua(value));
//
//                int value1[] = new int[3];
//                value1 = layBaSoNgauNhien(0, 51);
//
//                iv4.setImageResource(manghinhbai[value1[0]]);
//                iv5.setImageResource(manghinhbai[value1[1]]);
//                iv6.setImageResource(manghinhbai[value1[2]]);
//
//                tv_ketQuaMay2.setText(tinhKetQua(value1));
//            }
//        });

        CountDownTimer timer = new CountDownTimer(SoGiay, 1000) {

            int[] saulabai;
            int[] baso_May1;
            int[] baso_May2;

            int diemN = 0, diemM = 0;

            @Override
            public void onTick(long l) {

                tv_BoDem.setText("Thời gian còn lại là: " + l/1000 + " giây");

                luot++;
                if(luot == 1){
                    van ++;
                    tv_Van.setText("Ván: " + van);
                    saulabai = laySauSoNgauNhien(0,51);
                    baso_May1 = new int[3];
                    baso_May2 = new int[3];

                    for (int i = 0; i < 3; i++)
                        baso_May1[i] = saulabai[i];
                    for (int i = 0; i < 3; i++)
                        baso_May2[i] = saulabai[i+3];

                    iv1.setImageResource(0);
                    iv2.setImageResource(0);
                    iv3.setImageResource(0);
                    iv4.setImageResource(0);
                    iv5.setImageResource(0);
                    iv6.setImageResource(0);

                    tv_ketQuaMay1.setText("");
                    tv_ketQuaMay2.setText("");
                }
                if(luot == 2){
                    iv1.setImageResource(manghinhbai[baso_May1[0]]);
                    iv2.setImageResource(manghinhbai[baso_May1[1]]);
                    iv3.setImageResource(manghinhbai[baso_May1[2]]);
                    tv_ketQuaMay1.setText(tinhKetQua(baso_May1));
                    diemN = tinhSoNut(baso_May1);
                }
                if(luot == 3){
                    iv4.setImageResource(manghinhbai[baso_May2[0]]);
                    iv5.setImageResource(manghinhbai[baso_May2[1]]);
                    iv6.setImageResource(manghinhbai[baso_May2[2]]);
                    tv_ketQuaMay2.setText(tinhKetQua(baso_May2));
                    diemM = tinhSoNut(baso_May2);
                    tinhDiem(diemN, diemM);

                    luot = 0;
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(PlayingActivity.this, ScoreActivity.class);
                intent.putExtra("DiemMay1", tv_diemMay1.getText().toString().trim().substring(6));
                intent.putExtra("DiemMay2", tv_diemMay2.getText().toString().trim().substring(6));
                startActivityForResult(intent, 999);
            }
        };
        timer.start();
    }

    private String tinhKetQua(int [] value){
        String ketQua = "";
        if(tinhSoTay(value)==3){
            ketQua = "Kết Quả: 3 tây";
        }
        else {
            int tong = 0;
            for(int i = 0; i<value.length; i++){
                if(value[i]%13<10)
                    tong += value[i] % 13 +1;
            }
            if(tong % 10 == 0){
                ketQua = "Kết Quả bù, trong đó có " + tinhSoTay(value) + " tây.";
            }
            else{
                ketQua = "Kết Quả là " + (tong % 10) + " nút, trong đó có " + tinhSoTay(value) +
                        " tây.";
            }
        }
        return ketQua;
    }
    private int tinhSoTay(int a[]){
        int k = 0;
        for(int i=0;i<a.length;i++)
            if ((a[i] % 13 >= 10) && a[i] % 13 < 13)
                k++;
        return k;
    }
    private int[] laySauSoNgauNhien(int min, int max){
        int baso[] = new int[6];
        int i = 0;
        baso[i++] = random(min, max);
        do{
            int k = random(min, max);
            if(!kiemTraTrung(k,baso))
                baso[i++] = k;
        }while (i<6);
        return baso;
    }
    private boolean kiemTraTrung(int k, int a[]){
        for(int i = 0; i<a.length; i++){
            if(a[i]==k)
                return true;
        }
        return false;
    }

    private int random(int min, int max){
        return min + (int) (Math.random()*(max - min)+1);
    }

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
            tv_diemMay1.setText("Điểm: " + diemMay1);
        }else if(may1 < may2){
            Toast toast = Toast.makeText(this,"Máy 2 đã thắng !!!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 0);
            toast.show();
            diemMay2++;
            tv_diemMay2.setText("Điểm: " + diemMay2);
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

            tv_Van.setText("Ván: ");
            tv_diemMay1.setText("Điểm: 0");
            tv_diemMay2.setText("Điểm: 0");

            tv_ketQuaMay1.setText("");
            tv_ketQuaMay2.setText("2");

            iv1.setImageResource(0);
            iv2.setImageResource(0);
            iv3.setImageResource(0);
            iv4.setImageResource(0);
            iv5.setImageResource(0);
            iv6.setImageResource(0);

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}