package com.hoquochuy911.gk_listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.adservices.common.AdData;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgBtnAdd;
    private ListView lvContact;
    private ArrayList<Contact> contacts;
    private CustomAdapter adapter;
    private static final int ADD = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = findViewById(R.id.lv_contact);
        imgBtnAdd = findViewById(R.id.img_btn_add);

        contacts = new ArrayList<>();
        contacts.add(new Contact("Huy", "hoquochuy911@gmail.com", "0785955399"));
        contacts.add(new Contact("Huy", "hoquochuy911@gmail.com", "0785955399"));
        contacts.add(new Contact("Huy", "hoquochuy911@gmail.com", "0785955399"));

        adapter = new CustomAdapter(MainActivity.this, R.layout.custom_listview, contacts);
        lvContact.setAdapter(adapter);

        imgBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent,ADD);
            }
        });

        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Contact contact = contacts.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", contact);
                intent.putExtra("detail", bundle);
                startActivity(intent);
            }
        });
        registerForContextMenu(lvContact);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD){
            if(resultCode == RESULT_OK){
                Bundle bundle = data.getBundleExtra("add");
                Contact contact = (Contact) bundle.getSerializable("contact");
                contacts.add(contact);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.lv_contact){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.custom_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.add:

        }
        return super.onContextItemSelected(item);
    }
}