package com.hoquochuy911.nhanviensql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnNhanVien) {
            Intent intent1 = new Intent(MainActivity.this, NhanVienActivity.class);
            startActivity(intent1);
            return true;
        }
        if(item.getItemId() == R.id.mnPhongBan){
            Intent intent = new Intent(MainActivity.this, PhongBanActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            System.exit(0);
            return true;
        }
    }

}