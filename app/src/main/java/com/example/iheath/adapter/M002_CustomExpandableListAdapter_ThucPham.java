package com.example.iheath.adapter;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.iheath.R;
import com.example.iheath.entities.ThucPham;

public class M002_CustomExpandableListAdapter_ThucPham extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<ThucPham>> expandableListDetail;

    public M002_CustomExpandableListAdapter_ThucPham(Context context, List<String> expandableListTitle,
                                                     HashMap<String, List<ThucPham>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public ThucPham getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListTextName =  getChild(listPosition, expandedListPosition).getName();
        final Double expandedListTextCalo =  getChild(listPosition, expandedListPosition).getNangluong();
        ThucPham thucPham=  getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.m002_list_item, null);
        }
        TextView tenItem=  convertView
                .findViewById(R.id.tenItem_M002);
        TextView caloItem=  convertView
                .findViewById(R.id.caloItem_M002);
//        PieChart pieChartView= convertView.findViewById(R.id.chart_M002);
//        pieChartView.setUsePercentValues(true);
//
//        List<PieEntry> value= new ArrayList<>();
//        if (thucPham.getDam()>=5){
//            value.add(new PieEntry(thucPham.getDam().intValue(),"Chất đạm"));
//        }
//        if (thucPham.getBeo()>=5){
//            value.add(new PieEntry(thucPham.getBeo().intValue(),"Chất béo"));
//        }
//        if (thucPham.getXo()>=5){
//            value.add(new PieEntry(thucPham.getXo().intValue(),"Chất xơ"));
//        }
//        if (thucPham.getNuoc()>=5){
//            value.add(new PieEntry(thucPham.getNuoc().intValue(),"Nước"));
//        }
//        if (thucPham.getBot()>=5){
//            value.add(new PieEntry(thucPham.getBot().intValue(),"Tinh bột"));
//        }
//        int tong=0;
//        if (thucPham.getDam().intValue()<5){
//            tong= tong+thucPham.getDam().intValue();
//        }
//        if (thucPham.getBeo().intValue()<5){
//            tong= tong+thucPham.getBeo().intValue();
//        }
//        if (thucPham.getXo().intValue()<5){
//            tong= tong+thucPham.getXo().intValue();
//        }
//        if (thucPham.getNuoc().intValue()<5){
//            tong= tong+thucPham.getNuoc().intValue();
//        }
//        if (thucPham.getBot().intValue()<5){
//            tong= tong+thucPham.getBot().intValue();
//        }
//        if(tong!=0){
//            value.add(new PieEntry(tong,"Thành phần khác"));
//        }
//
//        PieDataSet pieDataSet = new PieDataSet(value,"Colors");
//        pieDataSet.setValueTextSize(15);
//        ArrayList<Integer> colors= new ArrayList<>();
//        for(int color: ColorTemplate.MATERIAL_COLORS){
//            colors.add(color);
//        }
//        for(int color: ColorTemplate.JOYFUL_COLORS){
//            colors.add(color);
//        }
//        PieData pieData= new PieData(pieDataSet);
//        pieData.setDrawValues(true);
//        pieData.setValueFormatter(new PercentFormatter());
//        pieDataSet.setColors(colors);
//        pieChartView.setData(pieData);
//        pieChartView.setCenterText(thucPham.getNangluong()+"\n Kcal");
//        pieChartView.setCenterTextSize(24);
//        pieChartView.setCenterTextColor(Color.RED);
//        pieChartView.setDescription(null);

        tenItem.setText(expandedListTextName);
        caloItem.setText(expandedListTextCalo+" kcal/100g");
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.m002_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}