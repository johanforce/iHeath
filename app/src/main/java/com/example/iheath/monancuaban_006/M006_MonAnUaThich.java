package com.example.iheath.monancuaban_006;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iheath.M001_menu;
import com.example.iheath.R;
import com.example.iheath.adapter.M006_ListAdapter;
import com.example.iheath.entities.RoomDatabase.MonAn;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.util.List;

public class M006_MonAnUaThich extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    M006_ListAdapter adapter;
    List<MonAn> listMonAn;
    Button btThemHoSo;
    public boolean flag= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m006_danhsachmonan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách món ăn");
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rc_listMonAn_m006);

        UserManager.getInstance().getAllMonAn(data -> {
            runOnUiThread(()->{
                listMonAn=(List<MonAn>) data;
                adapter = new M006_ListAdapter(listMonAn, this) {
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);

            });
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M006_MonAnUaThich.this, M001_menu.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
