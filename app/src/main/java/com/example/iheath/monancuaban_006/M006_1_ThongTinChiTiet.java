package com.example.iheath.monancuaban_006;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iheath.R;
import com.example.iheath.adapter.M006_1_ListAdapter;
import com.example.iheath.adapter.M006_ListAdapter;
import com.example.iheath.entities.RoomDatabase.MonAn;
import com.example.iheath.entities.RoomDatabase.ThanhPhanChiTiet;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.util.List;

public class M006_1_ThongTinChiTiet extends AppCompatActivity {
    private List<ThanhPhanChiTiet> thanhPhanChiTiets;
    private M006_1_ListAdapter adapter;
    private  RecyclerView recyclerView;
    private TextView tvTenMonAn, tvCaloMonAn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m006_1_thongtinchitiet);

        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin món ăn");
        actionBar.setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.rc_monan_m006_1);
        tvTenMonAn= findViewById(R.id.tv_tenMonAn_M006_1);
        tvCaloMonAn=findViewById(R.id.tv_calo_m006_1);
        Intent intent = getIntent();
        MonAn monAn = (MonAn) intent.getSerializableExtra(M006_ListAdapter.KEY_MONAN);
        tvTenMonAn.setText(monAn.getTenMonAn());
        tvCaloMonAn.setText("Lượng calo trong món ăn là: "+monAn.getCalo()+"calo");
        UserManager.getInstance().getAllThanhPhanMonAn(data -> {
            runOnUiThread(()->{
                thanhPhanChiTiets=(List<ThanhPhanChiTiet>) data;
                adapter = new M006_1_ListAdapter(thanhPhanChiTiets, this) {
                };

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            });
        }, monAn.getTenMonAn());

        UserManager.getInstance().getAllThanhPhan(data -> {
            List<ThanhPhanChiTiet> thanhPhanChiTiets= (List<ThanhPhanChiTiet>) data;
            Log.i("TAG1", thanhPhanChiTiets.toString());
        });
        UserManager.getInstance().getAllMonAn(data -> {
            List<MonAn> thanhPhanChiTiets= (List<MonAn>) data;
            Log.i("TAG1", thanhPhanChiTiets.toString());
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M006_1_ThongTinChiTiet.this, M006_MonAnUaThich.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
