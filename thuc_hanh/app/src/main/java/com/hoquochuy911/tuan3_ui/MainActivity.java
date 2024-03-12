package com.hoquochuy911.tuan3_ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int vitri;
    ListView listView;
    String[] listItem;
    Spinner sp_traicay;
    GridView gv_traicay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_chon = findViewById(R.id.button_chon);
        bt_chon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Done", Toast.LENGTH_LONG).show();
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Nofication");

                final CharSequence[] items = {"Red", "Yello", "Orange"};
                boolean[] arraycheck = {true, false, true};

                mydialog.setMultiChoiceItems(items, arraycheck, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        arraycheck[which] = isChecked;
                    }
                });
                mydialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String st = "";
                        for(int i = 0; i<items.length; i++){
                            if(arraycheck[i]){
                                st += items[i].toString();
                            }
                        }
                        Toast.makeText(MainActivity.this, st, Toast.LENGTH_LONG).show();
                    }
                });

//                mydialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, items[which].toString(), Toast.LENGTH_LONG).show();
//                    }
//                });

//                mydialog.setItems(items, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, items[which].toString(), Toast.LENGTH_LONG).show();
//                    }
//                });

//                mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "No done", Toast.LENGTH_LONG).show();
//                    }
//                });
//                mydialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "Yes done", Toast.LENGTH_LONG).show();
//                    }
//                });
                AlertDialog alertDialog = mydialog.create();
                alertDialog.show();
            }
        });

        //ListView
//        listView = findViewById(R.id.listview_traicay);
//        listItem = getResources().getStringArray(R.array.traicay_array);
//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1,
//                android.R.id.text1, listItem
//        );
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String value = adapter.getItem(i);
//                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
//            }
//        });

        //spinner
        sp_traicay.findViewById(R.id.spinner_traicay);
        listItem = getResources().getStringArray(R.array.traicay_array);
        ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listItem);
        sp_traicay.setAdapter(sp_adapter);
        sp_traicay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = listItem[i];
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gv_traicay = findViewById(R.id.gridview_traicay);
        listItem = getResources().getStringArray(R.array.traicay_array);
        ArrayAdapter<String> gv_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listItem);
        gv_traicay.setAdapter(gv_adapter);
        gv_traicay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String value = listItem[position];
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
        });
    }
}