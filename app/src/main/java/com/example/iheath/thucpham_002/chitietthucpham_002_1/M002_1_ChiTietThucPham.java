package com.example.iheath.thucpham_002.chitietthucpham_002_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.iheath.R;
import com.example.iheath.entities.ThucPham;
import com.example.iheath.thucpham_002.M002_ThucPham;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class M002_1_ChiTietThucPham extends AppCompatActivity {
    private TextView tvTenThucPham;
    private TextView tvLuongCalo;
    private TextView tvChatBeo, tvChatXo, tvChatBot, tvDam, tvNuoc;
    private ThucPham thucPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_1_chitietthucpham);
        initView();

        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi tiết thực phẩm");

        Intent intent= getIntent();
        thucPham= (ThucPham) intent.getSerializableExtra(M002_ThucPham.KEY_THUC_PHAM);

        tvLuongCalo= findViewById(R.id.tv_caloThucPham_007);
        tvTenThucPham=findViewById(R.id.tv_tenThucPham_007);
        tvChatBot=findViewById(R.id.tv_chatbot_m002_1);
        tvChatXo=findViewById(R.id.tv_chatxo_m002_1);
        tvChatBeo=findViewById(R.id.tv_chatbeo_m002_1);
        tvDam=findViewById(R.id.tv_chatdam_m002_1);
        tvNuoc=findViewById(R.id.tv_nuoc_m002_1);
        tvTenThucPham.setText("Tên loại thực phẩm: "+thucPham.getName());
        tvLuongCalo.setText("Lượng calo có trong 100gram là: "+ thucPham.getNangluong()+" calo");
        tvNuoc.setText("Nước: "+thucPham.getNuoc()+"g");
        tvDam.setText("Chất đạm: "+ thucPham.getDam()+"g");
        tvChatBeo.setText("Chất béo: "+ thucPham.getBeo()+"g");
        tvChatXo.setText("Chất xơ: "+ thucPham.getXo()+"g");
        tvChatBot.setText("Chất bột: "+ thucPham.getBot()+"g");
        bieuDoDinhDuong();
    }

    private void bieuDoDinhDuong() {
        PieChart pieChartView = findViewById(R.id.chart);
        pieChartView.setUsePercentValues(true);

        List<PieEntry> value= new ArrayList<>();
        if (thucPham.getDam()>=5){
            value.add(new PieEntry(thucPham.getDam().intValue(),"Chất đạm"));
        }
        if (thucPham.getBeo()>=5){
            value.add(new PieEntry(thucPham.getBeo().intValue(),"Chất béo"));
        }
        if (thucPham.getXo()>=5){
            value.add(new PieEntry(thucPham.getXo().intValue(),"Chất xơ"));
        }
        if (thucPham.getNuoc()>=5){
            value.add(new PieEntry(thucPham.getNuoc().intValue(),"Nước"));
        }
        if (thucPham.getBot()>=5){
            value.add(new PieEntry(thucPham.getBot().intValue(),"Tinh bột"));
        }
        int tong=0;
        if (thucPham.getDam().intValue()<5){
            tong= tong+thucPham.getDam().intValue();
        }
        if (thucPham.getBeo().intValue()<5){
            tong= tong+thucPham.getBeo().intValue();
        }
        if (thucPham.getXo().intValue()<5){
            tong= tong+thucPham.getXo().intValue();
        }
        if (thucPham.getNuoc().intValue()<5){
            tong= tong+thucPham.getNuoc().intValue();
        }
        if (thucPham.getBot().intValue()<5){
            tong= tong+thucPham.getBot().intValue();
        }
        if(tong!=0){
            value.add(new PieEntry(tong,"Thành phần khác"));
        }

        PieDataSet pieDataSet = new PieDataSet(value,"Colors");
        pieDataSet.setValueTextSize(15);
        ArrayList<Integer> colors= new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }
        for(int color: ColorTemplate.JOYFUL_COLORS){
            colors.add(color);
        }
        PieData pieData= new PieData(pieDataSet);
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieDataSet.setColors(colors);
        pieChartView.setData(pieData);
        pieChartView.setCenterText(thucPham.getNangluong()+"\n Kcal");
        pieChartView.setCenterTextSize(24);
        pieChartView.setCenterTextColor(Color.RED);
        pieChartView.setDescription(null);
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

}