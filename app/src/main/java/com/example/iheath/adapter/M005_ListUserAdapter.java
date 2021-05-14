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


import com.example.iheath.App;
import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.User;
import com.example.iheath.entities.RoomDatabase.UserDAO;
import com.example.iheath.theodoisuckhoe_005.M005_2_ThongTinChiTiet;

import java.util.List;

public abstract class M005_ListUserAdapter extends RecyclerView.Adapter<M005_ListUserAdapter.ViewHolder>{
    public static final String KEY_HO_SO="keyhoso";
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<User> listUser;
    private UserDAO userDAO;
    Handler handler;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public M005_ListUserAdapter(List<User> listUser, Context mContext) {
        this.listUser = listUser;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View userView =
                inflater.inflate(R.layout.m005_item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final User user = listUser.get(position);

        holder.tvTenUser.setText(user.getTenUser());
        holder.tvTenUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra(KEY_HO_SO,user);
                intent.setClass(mContext, M005_2_ThongTinChiTiet.class);
                mContext.startActivity(intent) ;
                Toast.makeText(mContext, user.toString(), Toast.LENGTH_SHORT).show();
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
                        listUser.remove(position);
                        new  Thread(){
                            @Override
                            public void run() {
                                try {
                                    App.getInstance().getUserDB().getUserDAO().xoaUser(user);
                                    App.getInstance().getUserDB().getUserDAO().xoaCanNangId(user.getIdUser());
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
        return listUser.size();
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
            tvTenUser = itemView.findViewById(R.id.tv_tenUser);
            ivRemove  = itemView.findViewById(R.id.iv_remove);

        }

        @Override
        public void onClick(View v) {

        }
    }

}