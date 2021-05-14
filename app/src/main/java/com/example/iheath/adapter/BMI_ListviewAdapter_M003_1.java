package com.example.iheath.adapter;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.BMI;

import java.util.ArrayList;

public class BMI_ListviewAdapter_M003_1 extends BaseAdapter {

    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<BMI> listBMI;

    public BMI_ListviewAdapter_M003_1(ArrayList<BMI> listBMI) {
        this.listBMI = listBMI;
    }

    @Override
    public int getCount() {
        return listBMI.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listBMI
        return listBMI.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listBMI.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewBMI;
        if (convertView == null) {
            viewBMI = View.inflate(parent.getContext(), R.layout.m003_list_item, null);
        } else viewBMI = convertView;

        //Bind sữ liệu phần tử vào View
        BMI bmi = (BMI) getItem(position);
        TextView ten= viewBMI.findViewById(R.id.tenItem_BMI_M003);
        ten.setText(bmi.getTen());
        TextView ngay= viewBMI.findViewById(R.id.ngayItem_BMI_M003);
        ngay.setText(bmi.getNgay());
        TextView bmi1= viewBMI.findViewById(R.id.bmiItem_BMI_M003);
        bmi1.setText("Chỉ số BMI: "+bmi.getBmi());


        return viewBMI;
    }

}