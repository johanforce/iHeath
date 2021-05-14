package com.example.iheath.theodoisuckhoe_005;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iheath.M001_menu;
import com.example.iheath.R;
import com.example.iheath.adapter.M005_ListUserAdapter;
import com.example.iheath.entities.RoomDatabase.User;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.util.List;

public class M005_DanhSachNguoiDung extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    M005_ListUserAdapter adapter;
    List<User> listUser;
    Button btThemHoSo;
    public boolean flag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m005_activity_danhsachnguoidung);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách người dùng");
        actionBar.setDisplayHomeAsUpEnabled(true);

        btThemHoSo=findViewById(R.id.DanhSachNguoiDung);
        btThemHoSo.setOnClickListener(this);
        recyclerView = findViewById(R.id.rc_listUser);

        UserManager.getInstance().getAllUser(data -> {
            runOnUiThread(()->{
                listUser=(List<User>) data;
                adapter = new M005_ListUserAdapter(listUser, this) {
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);

            });});
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.DanhSachNguoiDung){
            Intent intent= new Intent();
            intent.setClass(M005_DanhSachNguoiDung.this, M005_1_ThemHoSo.class);
            startActivity(intent) ;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M005_DanhSachNguoiDung.this, M001_menu.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
