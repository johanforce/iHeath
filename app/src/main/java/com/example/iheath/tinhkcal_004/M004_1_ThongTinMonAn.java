package com.example.iheath.tinhkcal_004;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iheath.R;
import com.example.iheath.adapter.M004_1_ThucPham_Adapter;
import com.example.iheath.entities.RoomDatabase.MonAnCuaBan;
import com.example.iheath.entities.ThucPhamCanTinhCalo;

import java.util.List;

public class M004_1_ThongTinMonAn extends AppCompatActivity implements View.OnClickListener {
    private final static Double CALO_MINUTES_DIBO=3.79;
    private final static Double CALO_MINUTES_CHAYBO=5.2;
    private final static Double CALO_MINUTES_DAPXE=8.67;
    private final static Double CALO_MINUTES_BOILOI=8.83;
    ProgressBar prgDiBo, prgDapXe, prgChayBo, prgBoiLoi;
    M004_1_ThucPham_Adapter adapter;
    TextView tvDiBo, tvDapXe, tvChayBo, tvBoiLoi, tvTenMonAn;
    Button bt_Favorite;
    MonAnCuaBan monAnCuaBan;
    RecyclerView rcMonAn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m004_1_thongtinmonan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin món ăn");
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        monAnCuaBan= (MonAnCuaBan) intent.getSerializableExtra(M004_TinhKcal.KEY_CHI_TIET_MON_AN);

        initView();
        chiTietMonAn();
        Log.i("TAG", monAnCuaBan.toString());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void chiTietMonAn() {
        tvTenMonAn.setText("Tên món ăn: "+ monAnCuaBan.getTenMonAn());

        tvDiBo.setText  ((int)(monAnCuaBan.getLuongCalo()/CALO_MINUTES_DIBO)+"");
        tvChayBo.setText((int)(monAnCuaBan.getLuongCalo()/CALO_MINUTES_CHAYBO)+"");
        tvDapXe.setText ((int)(monAnCuaBan.getLuongCalo()/CALO_MINUTES_DAPXE)+"");
        tvBoiLoi.setText((int)(monAnCuaBan.getLuongCalo()/CALO_MINUTES_BOILOI)+"");

        prgDiBo.setProgress((int) Double.parseDouble(tvDiBo.getText().toString()));
        prgDapXe.setProgress((int) Double.parseDouble(tvDapXe.getText().toString()));
        prgChayBo.setProgress((int) Double.parseDouble(tvChayBo.getText().toString()));
        prgBoiLoi.setProgress((int) Double.parseDouble(tvBoiLoi.getText().toString()));

    }

    private void initView() {
        prgBoiLoi= findViewById(R.id.prgBar_boiLoi);
        prgChayBo=findViewById(R.id.prgBar_ChayBo);
        prgDapXe=findViewById(R.id.prgBar_DapXe);
        prgDiBo=findViewById(R.id.prgBar_DiBo);

        tvBoiLoi=findViewById(R.id.tv_boiLoi);
        tvChayBo=findViewById(R.id.tv_ChayBo);
        tvDapXe=findViewById(R.id.tv_DapXe);
        tvDiBo=findViewById(R.id.tv_DiBo);

        List<ThucPhamCanTinhCalo> list= monAnCuaBan.getThucPhamCanTinhCalo();
        rcMonAn=findViewById(R.id.rc_listThucPham_M004_1);
        adapter = new M004_1_ThucPham_Adapter(list, this,monAnCuaBan.getTenMonAn()) {
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        rcMonAn.setAdapter(adapter);
        rcMonAn.setLayoutManager(linearLayoutManager);

        tvTenMonAn=findViewById(R.id.tv_tenMonAn_M004_1);
    }

    @Override
    public void onClick(View v) {
    }
}