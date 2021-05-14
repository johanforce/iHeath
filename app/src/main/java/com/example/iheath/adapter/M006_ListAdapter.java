package com.example.iheath.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.MonAn;
import com.example.iheath.entities.RoomDatabase.UserDAO;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.example.iheath.monancuaban_006.M006_1_ThongTinChiTiet;

import java.util.List;

public abstract class M006_ListAdapter extends RecyclerView.Adapter<M006_ListAdapter.ViewHolder>{
    public static final String KEY_MONAN="keymonan";
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<MonAn> listMonAn;
    private UserDAO userDAO;
    Handler handler;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public M006_ListAdapter(List<MonAn> listMonAn, Context mContext) {
        this.listMonAn = listMonAn;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View userView =
                inflater.inflate(R.layout.m006_monan, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final MonAn monAn= listMonAn.get(position);

        holder.tvTenUser.setText(monAn.getTenMonAn());
        holder.tvTenUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra(KEY_MONAN,monAn);
                intent.setClass(mContext, M006_1_ThongTinChiTiet.class);
                mContext.startActivity(intent) ;
                Toast.makeText(mContext, monAn.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(mContext);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn muốn xóa hồ sơ người dùng này?");
                dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listMonAn.remove(position);
                        new  Thread(){
                            @Override
                            public void run() {
                                try {
                                    UserManager.getInstance().xoaMonAn(data -> {}, monAn.getTenMonAn());
                                    UserManager.getInstance().xoaThanhPhan(data -> {}, monAn.getTenMonAn());
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        notifyItemRemoved(position);
                    }
                });
                dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMonAn.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View itemview;
        public TextView tvTenUser;
        public ImageView ivRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            tvTenUser = itemView.findViewById(R.id.tv_tenMonAn_m006);
            ivRemove  = itemView.findViewById(R.id.iv_remove_m006);

        }

        @Override
        public void onClick(View v) {

        }
    }

}