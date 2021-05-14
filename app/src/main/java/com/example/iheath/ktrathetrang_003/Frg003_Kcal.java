package com.example.iheath.ktrathetrang_003;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.Calo;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Frg003_Kcal extends Fragment implements View.OnClickListener {
    private EditText etCanNang, etChieuCao, etTuoi;
    private TextView tvKetQua;
    private Button btKetQua;
    private String sex, muc;
    double  TDEE;
    DecimalFormat f;
    private  String gioiTinh[]={"nam", "nữ",};
    private  String mucVanDong[]={"ít", "nhẹ","vừa","nặng","rất nặng"};
    public Frg003_Kcal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.m003_frg_kcal, container, false);
        setHasOptionsMenu(true);
        etCanNang= view.findViewById(R.id.etCanNang_Kcal003);
        etChieuCao=view.findViewById(R.id.etChieuCao_Kcal003);
        etTuoi=view.findViewById(R.id.etTuoi_Kcal003);
        btKetQua= view.findViewById(R.id.btKetQua_Kcal003);
        btKetQua.setOnClickListener(this);
        f= new DecimalFormat("##.##");

        gioiTinh(view);
        mucVanDong(view);
        return view;
    }

    public void gioiTinh(View view){
        Spinner spinner= view.findViewById(R.id.spinner_GioiTinh_Kcal003);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, gioiTinh);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sex= gioiTinh[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sex="";
            }
        });
    }

    public void mucVanDong(View view){
        Spinner spinner= view.findViewById(R.id.spinner_MucVanDong_Kcal003);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(view.getContext(),R.layout.support_simple_spinner_dropdown_item, mucVanDong);
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                muc= mucVanDong[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                muc="";
            }
        });
    }

    public void kcalTieuThu(){
        double mucVanDong = 0;
        if(muc=="ít"){
            mucVanDong = 1.2;
        }else if(muc=="nhẹ"){
            mucVanDong= 1.375;
        }else if(muc=="vừa"){
            mucVanDong= 1.55;
        }else if(muc=="nặng"){
            mucVanDong= 1.725;
        }else if(muc=="rất nặng"){
            mucVanDong= 1.9;
        }
        if(sex=="nam"){
            TDEE= (13.397*Double.parseDouble(etCanNang.getText().toString())+4.799*Double.parseDouble(etChieuCao.getText().toString())-5.677*Double.parseDouble(etTuoi.getText().toString())+88.362)* mucVanDong;
        }else if(sex=="nữ"){
            TDEE= mucVanDong*(9.247*Double.parseDouble(etCanNang.getText().toString())+3.098*Double.parseDouble(etChieuCao.getText().toString())-4.330*Double.parseDouble(etTuoi.getText().toString())+447.593);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set Title and Message:

        builder.setTitle("Thông báo").setMessage("Số calo mà bạn tiêu thụ trong một ngày là: "+ f.format(TDEE));


        // Create "Yes" button with OnClickListener.
        builder.setPositiveButton("Trở về", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        // Create AlertDialog:
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btKetQua_Kcal003){
            kcalTieuThu();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.iv_additem){
           android.app.AlertDialog.Builder dialog= new android.app.AlertDialog.Builder(getActivity());
            View view= getLayoutInflater().inflate(R.layout.m003_add_item,null);
            dialog.setView(view);
            dialog.setTitle("Luu du lieu");
            dialog.setMessage("Xin moi ban nhap ten:");
            dialog.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText editText= view.findViewById(R.id.et_ten);
                    Calendar calendar= Calendar.getInstance();
                    SimpleDateFormat dateFormat;
                    dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String date = dateFormat.format(calendar.getTime());
                    UserManager.getInstance().getAllCalo(data -> {
                        getActivity().runOnUiThread(()->{
                            List<Calo> list1 = (List<Calo>) data;
                            int xl;
                            if(list1.size()==0){
                                xl=1;
                            }
                            else
                                xl = list1.get(list1.size()-1).getId();
                            double chiSoCalo= Double.parseDouble(f.format(TDEE).replace(",","."));
                            Calo calo= new Calo(xl+1, date, chiSoCalo, sex,muc,Integer.parseInt(etTuoi.getText().toString()), Double.parseDouble(etChieuCao.getText().toString()),Double.parseDouble(etCanNang.getText().toString()),editText.getText().toString());
                            UserManager.getInstance().themCalo(data1 -> {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Them thanh cong!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }, calo);
                        });
                    });
                }
            });
            dialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            android.app.AlertDialog alert = dialog.create();
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
