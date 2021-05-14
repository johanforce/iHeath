package com.example.iheath.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.ThanhPhanChiTiet;
import com.example.iheath.entities.RoomDatabase.UserDAO;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.example.iheath.entities.ThucPhamCanTinhCalo;

import java.util.ArrayList;
import java.util.List;

public abstract class M004_1_ThucPham_Adapter extends RecyclerView.Adapter<M004_1_ThucPham_Adapter.ViewHolder>{
    public static final String KEY_HO_SO="keyhoso";
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<ThucPhamCanTinhCalo> thucPhamCanTinhCaloList;
    private UserDAO userDAO;
    Handler handler;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;
    private String tenMonAn;

    public M004_1_ThucPham_Adapter(List<ThucPhamCanTinhCalo> thucPhamCanTinhCaloList, Context mContext, String tenMonAn) {
        this.thucPhamCanTinhCaloList=thucPhamCanTinhCaloList;
        this.mContext = mContext;
        this.tenMonAn=tenMonAn;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View userView =
                inflater.inflate(R.layout.m004_item_thucpham_thanhphan, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        final ThucPhamCanTinhCalo thucPhamCanTinhCalo = thucPhamCanTinhCaloList.get(position);
        holder.tvTenThucPham.setText(thucPhamCanTinhCalo.getTenSanPham());
        holder.tvThongTin.setText(thucPhamCanTinhCalo.getSoLuong()+" gram- "+thucPhamCanTinhCalo.getCalo()+" calo");

    }

    @Override
    public int getItemCount() {
        return thucPhamCanTinhCaloList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View itemview;
        public TextView tvTenThucPham, tvThongTin;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvTenThucPham = itemView.findViewById(R.id.tv_tenThucPham_M004_1);
            tvThongTin  = itemView.findViewById(R.id.tv_caloThucPham_M004_1);
        }

        @Override
        public void onClick(View v) {

        }
    }

}