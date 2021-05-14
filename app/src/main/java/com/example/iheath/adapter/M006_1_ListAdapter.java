package com.example.iheath.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.ThanhPhanChiTiet;
import com.example.iheath.entities.RoomDatabase.UserDAO;

import java.util.List;

public abstract class M006_1_ListAdapter extends RecyclerView.Adapter<M006_1_ListAdapter.ViewHolder>{
    public static final String KEY_HO_SO="keyhoso";
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<ThanhPhanChiTiet> listThanhPhan;
    private UserDAO userDAO;
    Handler handler;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public M006_1_ListAdapter(List<ThanhPhanChiTiet> listThanhPhan, Context mContext) {
        this.listThanhPhan = listThanhPhan;
        this.mContext = mContext;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ThanhPhanChiTiet thanhPhanChiTiet= listThanhPhan.get(position);

        holder.tvTenThanhPhan.setText(thanhPhanChiTiet.getTenThanhPhan());
        holder.tvKhoiLuong.setText(thanhPhanChiTiet.getSoLuong()+"g - "+ thanhPhanChiTiet.getCalo()+"calo");
    }

    @Override
    public int getItemCount() {
        return listThanhPhan.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View itemview;
        public TextView tvTenThanhPhan, tvKhoiLuong;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvTenThanhPhan = itemView.findViewById(R.id.tv_tenThucPham_M004_1);
            tvKhoiLuong= itemView.findViewById(R.id.tv_caloThucPham_M004_1);
        }

        @Override
        public void onClick(View v) {

        }
    }

}