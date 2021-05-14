package com.example.iheath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.iheath.tintuc_007.M007_TinTuc;
import com.example.iheath.tintuc_007.danhsachchitiet.M007_1_DanhSachTinTuc;

public class M009_Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m009_info);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tin tức");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M009_Info.this, M001_menu.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}