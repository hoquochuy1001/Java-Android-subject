package com.hoquochuy911.xulyfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFER_NAME = "Reg";
    private SharedPreferences sharedPreferences;

    UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_createAccount = findViewById(R.id.tv_createAccount);
        EditText edt_userName = findViewById(R.id.edt_userName);
        EditText edt_passWord = findViewById(R.id.edt_passWord);
        Button btnLogin = findViewById(R.id.btnLogin);

        tv_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAccountActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_userName.getText().toString();
                String passWord = edt_passWord.getText().toString();
                if(TextUtils.isEmpty(userName)){
                    edt_userName.setError("User name is Required");
                    return;
                }
                if(TextUtils.isEmpty(passWord)){
                    edt_passWord.setError("Pass word is Required");
                    return;
                }
                if(userName.trim().length() > 0 && passWord.trim().length()>0){

                    String uName = null;
                    String uPassword =null;

                    if(sharedPreferences.contains("UserName")){
                        uName = sharedPreferences.getString("UserName","");
                    }
                    if(sharedPreferences.contains("PassWord")){
                        uPassword = sharedPreferences.getString("PassWord","");
                    }
                    if(userName.equals(uName) && passWord.equals(uPassword)){
                        session.createUserLoginSession(uName, uPassword);

                        Intent intent = new Intent(getApplicationContext(),MyAppActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "UserName/PassWord is incorrect", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}