package com.hoquochuy911.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {
    EditText edt_id, edt_name, edt_address, edt_email;
    Button bt_save, bt_select, bt_exit, bt_update, bt_delete;
    GridView gv_display;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_address = findViewById(R.id.edt_address);
        edt_email = findViewById(R.id.edt_email);

        bt_save = findViewById(R.id.btn_save);
        bt_select = findViewById(R.id.btn_select);
        bt_exit = findViewById(R.id.button_exit);
        bt_update = findViewById(R.id.btn_update);
        bt_delete = findViewById(R.id.btn_delete);

        gv_display = findViewById(R.id.gridview_display);
        databaseHelper = new DatabaseHelper(this);

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();
                author.setId_author(Integer.parseInt(edt_id.getText().toString()));
                author.setName(edt_name.getText().toString());
                author.setAddress(edt_address.getText().toString());
                author.setEmail(edt_email.getText().toString());
                if (databaseHelper.insertAuthor(author) > 0)
                    Toast.makeText(AuthorActivity.this, "Done", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AuthorActivity.this, "Nahh", Toast.LENGTH_SHORT).show();
            }
        });

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Author author = new Author();

                try {
                    author.setId_author(Integer.parseInt(edt_id.getText().toString()));
                } catch (NumberFormatException e) {
                    Toast.makeText(AuthorActivity.this, "Invalid id_author. Please enter a valid integer.", Toast.LENGTH_SHORT).show();
                    return; // Thoát khỏi phương thức nếu id_author không hợp lệ
                }

                author.setName(edt_name.getText().toString());
                author.setAddress(edt_address.getText().toString());
                author.setEmail(edt_email.getText().toString());

                if (databaseHelper.updateAuthor(author) > 0)
                    Toast.makeText(AuthorActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AuthorActivity.this, "id_author không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Author> list_author = databaseHelper.getAllAuthor();
                ArrayList<String> list_string = new ArrayList<>();

                for(Author author:list_author){
                    list_string.add(author.getId_author()+"");
                    list_string.add(author.getName());
                    list_string.add(author.getAddress());
                    list_string.add(author.getEmail());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AuthorActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gv_display.setAdapter(adapter);

                gv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Vì mỗi tác giả có 4 trường thông tin, vì vậy chúng ta cần chia vị trí cho 4 để lấy đúng tác giả
                        Author selectedAuthor = list_author.get(position / 4);

                        // Đặt thông tin của tác giả đã chọn vào các trường EditText
                        edt_id.setText(String.valueOf(selectedAuthor.getId_author()));
                        edt_name.setText(selectedAuthor.getName());
                        edt_address.setText(selectedAuthor.getAddress());
                        edt_email.setText(selectedAuthor.getEmail());
                    }
                });
            }
        });
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int id_author = Integer.parseInt(edt_id.getText().toString());

                    if (databaseHelper.deleteAuthor(id_author) > 0) {
                        Toast.makeText(AuthorActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AuthorActivity.this, "id_author không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(AuthorActivity.this, "Invalid id_author. Please enter a valid integer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
