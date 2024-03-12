package com.hoquochuy911.gk_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {
    private EditText edtName, edtEmail, edtPhone;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhone = findViewById(R.id.edt_phone);

        btnSave = findViewById(R.id.btn_save);

        Intent intent = getIntent();
        if(!intent.hasExtra("edit")){
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Contact contact = new Contact(
                            edtName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtPhone.getText().toString()
                    );
                    Intent intent1 = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("contact",contact);
                    intent1.putExtra("add", bundle);
                    setResult(RESULT_OK, intent1);
                    finish();
                }
            });
        }
    }
}