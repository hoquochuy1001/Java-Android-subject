package com.hoquochuy911.xulyfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewAccountActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        EditText edt_userName = findViewById(R.id.edt_userName);
        EditText edt_passWord = findViewById(R.id.edt_passWord);
        Button btnCreate = findViewById(R.id.btnCreate);

        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
        editor = sharedPreferences.edit();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edt_userName.getText().toString();
                String passWord = edt_passWord.getText().toString();

                editor.putString("Name", userName);
                editor.putString("txtPassword",passWord);
                editor.commit();   //

                Intent intent = new Intent(NewAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}