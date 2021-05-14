package com.example.iheath.theodoisuckhoe_005;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.CanNang;
import com.example.iheath.entities.RoomDatabase.User;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class M005_3_TheoDoiCanNang extends AppCompatActivity implements OnChartValueSelectedListener, View.OnClickListener {
    EditText etCan;
    Button btShow;
    private CombinedChart mChart;
    User user;
    List<Double> listCanNang= new ArrayList<>();
    List<String> listNgay = new ArrayList<>();
    List<CanNang> listCanNangUser= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m005_3_activity_theo_doi_can_nang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Theo dõi cân nặng");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        etCan= findViewById(R.id.tv_can);
        btShow=findViewById(R.id.bt_Show);
        btShow.setOnClickListener(this);

        mChart = findViewById(R.id.combinedChart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.getBackground().setAlpha(60);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(this);
    }

    private void initView() {
        Intent intent= getIntent();
        user= (User) intent.getSerializableExtra(M005_2_ThongTinChiTiet.KEY_USER_CAN_NANG);
        UserManager.getInstance().getCanNangUser(data -> {
            runOnUiThread(()->{
                listCanNangUser=(List<CanNang>) data;
                for (int i=0;i<listCanNangUser.size();i++){
                    listCanNang.add(listCanNangUser.get(i).getCanNangUser());
                    listNgay.add(listCanNangUser.get(i).getNgay()+"");
                    settingData();
                }
                Log.i("TAG", listCanNangUser.toString());
            });},user.getIdUser());
    }

    private void settingData() {
//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f);
//
//        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMinimum(0f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(listNgay));

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart());

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        Log.i("TAG", h.getX()+"");
//        int x= (int) h.getX();
//        UserManager.getInstance().xoaCanNangTheoNgay(data -> {
//
//        }, x+1);
//        Intent intent = getIntent();
//        finish();
//        startActivity(intent);
        Toast.makeText(this, "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private DataSet dataChart() {

        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < listCanNang.size(); index++) {
            entries.add(new Entry(index, listCanNang.get(index).floatValue()));
        }

        LineDataSet set = new LineDataSet(entries, "Request Ots approved");
        set.setColor(Color.BLUE);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.BLACK);
        set.setCircleRadius(5f);
        set.setFillColor(Color.BLACK);
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_Show){
            listNgay.add(listNgay.size()+1+"");
            listCanNang.add(Double.parseDouble(etCan.getText().toString()));
            settingData();
            UserManager.getInstance().getAllCanNang(data2 -> {
                runOnUiThread(() -> {
                    List<CanNang> list1 = (List<CanNang>) data2;
                    int xl;
                    if(list1.size()==0) xl=0;
                    else xl= list1.get(list1.size() - 1).getIdCanNang();
                    UserManager.getInstance().getCanNangUser(data3 -> {
                        runOnUiThread(() -> {
                            List<CanNang> listNgay= (List<CanNang>) data3;
                            int ngay=0;
                            for(int i=0;i<listNgay.size();i++) {
                                if (ngay < listNgay.get(i).getNgay()) {
                                    ngay=listNgay.get(i).getNgay();
                                }
                            }
                            CanNang canNang= new CanNang(xl+1,user.getIdUser(), ngay+1,Double.parseDouble(etCan.getText().toString()));
                            UserManager.getInstance().themCanNangUser(data4 -> {
                                runOnUiThread(()->{
                                });},canNang);
                        });
                    }, user.getIdUser());
                });
            });
        }
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