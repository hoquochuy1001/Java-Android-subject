package com.hoquochuy911.nhanviensql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhongBanActivity extends AppCompatActivity {

    EditText edt_maPB, edt_tenPB;
    Button btn_xem, btn_them, btn_xoa, btn_sua, btn_thoat;
    GridView gridView;
    DataHelper dataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        edt_maPB = findViewById(R.id.edt_maPB);
        edt_tenPB = findViewById(R.id.edt_tenPB);

        btn_them = findViewById(R.id.btnThem);
        btn_xem = findViewById(R.id.btnXem);
        btn_xoa = findViewById(R.id.btnXoa);
        btn_sua = findViewById(R.id.btnSua);
        btn_thoat = findViewById(R.id.button_exit);

        gridView = findViewById(R.id.gridview_display);

        dataHelper = new DataHelper(this);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongBan pb = new PhongBan();
                pb.setMaPB(edt_maPB.getText().toString());
                pb.setTenPB(edt_tenPB.getText().toString());
                if(dataHelper.insertPhongBan(pb)>0){
                    Toast.makeText(PhongBanActivity.this, "Done", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(PhongBanActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PhongBan> list_pb = dataHelper.getAllPhongBan();
                ArrayList<String> list_string = new ArrayList<>();

                for(PhongBan pb : list_pb){
                    list_string.add(pb.getMaPB());
                    list_string.add(pb.getTenPB());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(PhongBanActivity.this,
                        android.R.layout.simple_list_item_1, list_string);
                gridView.setAdapter(adapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PhongBan selectPB = list_pb.get(position/2);
                        edt_maPB.setText(selectPB.getMaPB());
                        edt_tenPB.setText(selectPB.getTenPB());
                    }
                });
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongBan pb = new PhongBan();
                try {
                    pb.setMaPB(edt_maPB.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(PhongBanActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    return; // Thoát khỏi phương thức nếu id_author không hợp lệ
                }
                pb.setMaPB(edt_maPB.getText().toString());
                pb.setTenPB(edt_tenPB.getText().toString());

                if (dataHelper.updatePhongBan(pb) > 0)
                    Toast.makeText(PhongBanActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PhongBanActivity.this, "Mã phòng ban không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = edt_maPB.getText().toString();

                    if (dataHelper.deletePhongBan(id) > 0) {
                        Toast.makeText(PhongBanActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PhongBanActivity.this, "Mã phòng ban không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(PhongBanActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }

}