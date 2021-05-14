package com.example.iheath.thucpham_002;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iheath.R;
import com.example.iheath.adapter.M002_CustomExpandableListAdapter_ThucPham;
import com.example.iheath.entities.ThucPham;
import com.example.iheath.thucpham_002.chitietthucpham_002_1.M002_1_ChiTietThucPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class M002_ThucPham extends AppCompatActivity {
    public final static String KEY_THUC_PHAM= "key_thuc_pham";
    List<ThucPham> listThucPham;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<ThucPham>> expandableListDetail;
    RecyclerView rvUsers;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_thucpham);
        Intent intent= getIntent();
        listThucPham= (List<ThucPham>) intent.getSerializableExtra("TAG");

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new M002_CustomExpandableListAdapter_ThucPham(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                ThucPham thucPham = null;
                for(int i=0;i<listThucPham.size();i++){
                    if(expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition).equals(listThucPham.get(i))){
                        thucPham= listThucPham.get(i);
                        Intent intent1= new Intent();
                        intent.putExtra(KEY_THUC_PHAM,thucPham);
                        intent.setClass(M002_ThucPham.this, M002_1_ChiTietThucPham.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    public  HashMap<String, List<ThucPham>> getData() {
        Intent intent= getIntent();
        listThucPham = (List<ThucPham>) intent.getSerializableExtra("TAG");
        HashMap<String, List<ThucPham>> listLoaiThucPham = new HashMap<String, List<ThucPham>>();
        List<ThucPham> listThit = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Thịt")){
                listThit.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listHaiSan = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Thủy hải sản")){
                listHaiSan.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listDoNgot = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Đồ ngọt")){
                listDoNgot.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listSua = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Sữa")){
                listSua.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listMiAnLien = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Cháo, phở, miến, mì ăn liền")){
                listMiAnLien.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listHat = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Hạt giàu đạm và chất béo")){
                listHat.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listCu = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Củ giàu tinh bột")){
                listCu.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listNguCoc = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Ngũ cốc")){
                listNguCoc.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listGiau = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Dầu, mỡ, bơ")){
                listGiau.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listDoHop = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Đồ hộp")){
                listDoHop.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listGiaVi = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Gia vị, nước chấm")){
                listGiaVi.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listQua = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Quả chín")){
                listQua.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listRau = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Rau và củ quả dùng làm rau")){
                listRau.add(listThucPham.get(i));
            }
        }
        List<ThucPham> listTrung = new ArrayList<>();
        for (int i = 0; i < listThucPham.size();i++){
            if(listThucPham.get(i).getNhomthucpham().equals("Trứng")){
                listTrung.add(listThucPham.get(i));
            }
        }

        listLoaiThucPham.put("Thịt", listThit);
        listLoaiThucPham.put("Hải sản", listHaiSan);
        listLoaiThucPham.put("Đồ ngọt", listDoNgot);
        listLoaiThucPham.put("Cháo, phở, miến, mì ăn liền", listMiAnLien);
        listLoaiThucPham.put("Hạt giàu đạm và chất béo", listHat);
        listLoaiThucPham.put("Ngũ cốc", listNguCoc);
        listLoaiThucPham.put("Củ giàu tinh bột", listCu);
        listLoaiThucPham.put("Dầu, mỡ, bơ", listGiau);
        listLoaiThucPham.put("Đồ hộp", listDoHop);
        listLoaiThucPham.put("Gia vị, nước chấm", listGiaVi);
        listLoaiThucPham.put("Quả chín", listQua);
        listLoaiThucPham.put("Rau và củ quả dùng làm rau", listRau);
        listLoaiThucPham.put("Trứng", listTrung);
        listLoaiThucPham.put("Sữa", listSua);
        return listLoaiThucPham;
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
