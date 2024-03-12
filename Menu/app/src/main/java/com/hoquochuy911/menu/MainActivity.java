package com.hoquochuy911.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final String STATE = "trang thai ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(STATE, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(STATE, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(STATE, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(STATE, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(STATE, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(STATE, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(STATE, "onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e(STATE, "onSave");
    }
}