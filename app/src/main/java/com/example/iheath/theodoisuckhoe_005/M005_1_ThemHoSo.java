package com.example.iheath.theodoisuckhoe_005;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.CanNang;
import com.example.iheath.entities.TinhToan;
import com.example.iheath.entities.RoomDatabase.User;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.util.List;

public class M005_1_ThemHoSo extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_THEM_USER="themuser";
    private String cuongDoVanDong[]={"ít", "nhẹ","vừa","nặng","rất nặng"};
    private String gioiTinh[]={"nam", "nữ",};
    private EditText etTenUser, etTuoiUser, etChieuCaoUser, etCanNangUser;
    private Spinner spGioiTinhUser, spCuongDoUser;
    private CheckBox cbMale,cbFemale;
    private String sex, cuongDo;
    private Button btThemHoSo;
    private int id=3;
    private List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m005_1_activity_them_ho_so);

        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm hồ sơ");
        actionBar.setDisplayHomeAsUpEnabled(true);

        etCanNangUser=findViewById(R.id.et_canNangUser);
        etChieuCaoUser=findViewById(R.id.et_chieuCaoUser);
        etTenUser=findViewById(R.id.et_tenUser);
        etTuoiUser=findViewById(R.id.et_tuoiUser);
        btThemHoSo=findViewById(R.id.bt_themHoSo);
        btThemHoSo.setOnClickListener(this);
        gioiTinh();
        cuongDoVanDong();
    }

    public void cuongDoVanDong(){
        spCuongDoUser= findViewById(R.id.spinner_cuongDo);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, cuongDoVanDong);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spCuongDoUser.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spCuongDoUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cuongDo= cuongDoVanDong[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                cuongDo="";
            }
        });
    }

    public void gioiTinh(){
//        spGioiTinhUser= findViewById(R.id.spinner_gioiTinhUser);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,gioiTinh);
//        adapter.setDropDownViewResource
//                (android.R.layout.simple_spinner_dropdown_item);
//        spGioiTinhUser.setAdapter(adapter);
//        //thiết lập sự kiện chọn phần tử cho Spinner
//        spGioiTinhUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                sex= gioiTinh[position];
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                sex="";
//            }
//        });
        cbFemale= findViewById(R.id.cb_female);
        cbMale= findViewById(R.id.cb_Male);
        CompoundButton.OnCheckedChangeListener m_listener
                = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton == cbFemale)
                {
                   cbMale.setChecked(!b);
                   sex="nữ";
                }
                else {
                    cbFemale.setChecked(!b);
                    sex="nam";
                }
            }
        };
        cbMale.setOnCheckedChangeListener(m_listener);
        cbFemale.setOnCheckedChangeListener(m_listener);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_themHoSo){
            UserManager.getInstance().getAllUser(data -> {
                runOnUiThread(()->{
                    list=(List<User>) data;
                    TinhToan toan= new TinhToan();
                    double x=toan.tinhCaloTieuThu(Double.parseDouble(etChieuCaoUser.getText().toString()), Double.parseDouble(etCanNangUser.getText().toString()), Integer.parseInt(etTuoiUser.getText().toString()),sex,cuongDo);
                    int cl;
                    if(list.size()==0) cl=0;
                    else cl= list.get(list.size()-1).getIdUser();
                    User user= new User(cl+1,etTenUser.getText().toString(),sex,cuongDo, Integer.parseInt(etTuoiUser.getText().toString()), Double.parseDouble(etChieuCaoUser.getText().toString()), Double.parseDouble(etCanNangUser.getText().toString()),0.0,0);
                    UserManager.getInstance().themUser(data2 -> {
                        runOnUiThread(()->{
                        });},user);
                    UserManager.getInstance().getAllCanNang(data2 -> {
                        runOnUiThread(()->{
                            List<CanNang> list1= (List<CanNang>) data2;
                            int xl= list1.get(list1.size()-1).getIdCanNang();
                            CanNang canNang= new CanNang(xl+1,user.getIdUser(),1,Double.parseDouble(etCanNangUser.getText().toString()));
                            UserManager.getInstance().themCanNangUser(data3 -> {
                                runOnUiThread(()->{
                                });},canNang);
                        });});
                    Intent intent= new Intent();
                    intent.putExtra(KEY_THEM_USER, user);
                    intent.setClass(this, M005_2_ThongTinChiTiet.class);
                    startActivity(intent);
                });});
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
           finish();
        }
        return super.onOptionsItemSelected(item);
    }
}