package com.hoquochuy911.onthizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;

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
        if(item.getItemId() == R.id.mnSach){
            Intent intent = new Intent(MainActivity.this, SachActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.mnTacGia){
            Intent intent = new Intent(MainActivity.this, TacGiaActivity.class);
            startActivity(intent);
            return true;
        }
        else{
            System.exit(0);
            return true;
        }
    }
}