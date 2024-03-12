package com.hoquochuy911.nhanviensql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NhanVienActivity extends AppCompatActivity {


    private static final int SELECT_PICTURE = 1;
    String selectedImagePath;
    ImageView imageView;
    EditText edt_manv, edt_tennv;
    Button btn_img, btn_xem, btn_them, btn_thoat, btn_xoa, btn_sua;
    Spinner spinner;
    RecyclerView recyclerView;
    RadioButton rd_nam, rd_nu;
    RadioGroup rd_group;
    DataHelper dataHelper;

    PhongBan selectedPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        imageView = findViewById(R.id.imgview_img);
        dataHelper = new DataHelper(this);

        edt_manv=findViewById(R.id.maNV);
        edt_tennv=findViewById(R.id.tenNV);

        btn_img=findViewById(R.id.btn_img);
        btn_them = findViewById(R.id.btnThem);
        btn_xem = findViewById(R.id.btnXem);
        btn_xoa = findViewById(R.id.btnXoa);
        btn_thoat = findViewById(R.id.btnThoat);
        btn_sua = findViewById(R.id.btnSua);

        recyclerView = findViewById(R.id.recyclerView);

        spinner = findViewById(R.id.spiDonVi);

        rd_nam = findViewById(R.id.radNam);
        rd_nu = findViewById(R.id.radNu);
        rd_group = findViewById(R.id.radGroup);

        dataHelper = new DataHelper(this);

        ArrayList<PhongBan> list_pb = dataHelper.getAllPhongBan();
        ArrayList<String> list_maPB = new ArrayList<>();
        for (PhongBan pb : list_pb) {
            list_maPB.add(pb.getMaPB());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_maPB);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMaPB = (String) parent.getItemAtPosition(position);
                selectedPhongBan = dataHelper.getPhongBan(selectedMaPB);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedPhongBan = null;
            }
        });

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            }
        });
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(edt_manv.getText().toString());
                nv.setTenNV(edt_tennv.getText().toString());
                nv.setImg(selectedImagePath);
                nv.setGender(rd_nam.isChecked() ? Enum.Gender.Nam : Enum.Gender.Nữ);
                nv.setPb(selectedPhongBan);
                if(dataHelper.insertNhanVien(nv)>0){
                    Toast.makeText(NhanVienActivity.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(NhanVienActivity.this, "Lỗi khi thêm nhân viên", Toast.LENGTH_SHORT).show();
            }
        });
        btn_xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<NhanVien> list_nv = dataHelper.getAllNhanVien();
                NhanVienAdapter adapter = new NhanVienAdapter(NhanVienActivity.this, list_nv);
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(NhanVienActivity.this));
                adapter.setOnItemClickListener(new NhanVienAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        NhanVien selectedNV = list_nv.get(position);
                        edt_manv.setText(selectedNV.getMaNV());
                        edt_tennv.setText(selectedNV.getTenNV());
                        displayImage(selectedNV.getImg());
                        if (selectedNV.getGender() == Enum.Gender.Nam) {
                            rd_nam.setChecked(true);
                        } else {
                            rd_nu.setChecked(true);
                        }
                        selectPhongBanInSpinner(selectedNV.getPb());
                    }
                });
            }
        });
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = edt_manv.getText().toString();

                    if (dataHelper.deleteNhanVien(id) > 0) {
                        Toast.makeText(NhanVienActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NhanVienActivity.this, "Mã nhân viên không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(NhanVienActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nv = new NhanVien();
                try{
                    nv.setMaNV(edt_manv.getText().toString());
                }catch (NumberFormatException e){
                    Toast.makeText(NhanVienActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;
                }

                nv.setMaNV(edt_manv.getText().toString());
                nv.setTenNV(edt_tennv.getText().toString());
                nv.setImg(selectedImagePath);
                nv.setGender(rd_nam.isChecked() ? Enum.Gender.Nam : Enum.Gender.Nữ);
                nv.setPb(selectedPhongBan);
                if (dataHelper.updateNhanVien(nv) > 0)
                    Toast.makeText(NhanVienActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(NhanVienActivity.this, "Mã nhân viên không tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void selectPhongBanInSpinner(PhongBan phongBan) {
        int position = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(phongBan.getMaPB());
        spinner.setSelection(position);
    }
    public void displayImage(String imagePath) {
        ImageView imageView = findViewById(R.id.imgview_img);
        if (imagePath != null && !imagePath.isEmpty()) {
            imageView.setImageURI(Uri.parse(imagePath));
        } else {
            imageView.setImageResource(R.drawable.profile);
        }
    }
    public String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                imageView.setImageURI(selectedImageUri);
            }
        }
    }
}
