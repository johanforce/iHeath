package com.example.iheath.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.CaloCanNap;

import java.util.ArrayList;

public class CaloCanNap_ListviewAdapter_M003_1 extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<CaloCanNap> listCaloCanNap;

    public CaloCanNap_ListviewAdapter_M003_1(ArrayList<CaloCanNap> listCaloCanNap) {
        this.listCaloCanNap = listCaloCanNap;
    }

    @Override
    public int getCount() {
        return listCaloCanNap.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listBMI
        return listCaloCanNap.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listCaloCanNap.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewBMI;
        if (convertView == null) {
            viewBMI = View.inflate(parent.getContext(), R.layout.m003_list_item, null);
        } else viewBMI = convertView;

        //Bind sữ liệu phần tử vào View
        CaloCanNap caloCanNap = (CaloCanNap) getItem(position);
        TextView ten= viewBMI.findViewById(R.id.tenItem_BMI_M003);
        ten.setText(caloCanNap.getTen());
        TextView ngay= viewBMI.findViewById(R.id.ngayItem_BMI_M003);
        ngay.setText(caloCanNap.getNgay());
        TextView bmi1= viewBMI.findViewById(R.id.bmiItem_BMI_M003);
        bmi1.setText("Calo cần nạp: "+caloCanNap.getCaloCanNap());


        return viewBMI;
    }
}
