package com.example.iheath.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.RSS;
import com.example.iheath.tintuc_007.rssNews.M007_ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by reale on 5/5/2017.
 */

class M007_1_FeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
{

    public TextView txtTitle,txtPubDate,txtContent;
    public ImageView iv;
    private M007_ItemClickListener itemClickListener;

    public M007_1_FeedViewHolder(View itemView) {
        super(itemView);

        iv= itemView.findViewById(R.id.iv);
        txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
        txtPubDate = (TextView)itemView.findViewById(R.id.txtPubDate);
        txtContent = (TextView)itemView.findViewById(R.id.txtContent);

        //Set Event
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(M007_ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class M007_1_FeedAdapter extends RecyclerView.Adapter<M007_1_FeedViewHolder> {

    private ArrayList<RSS> listRSS;
    private Context mContext;
    private LayoutInflater inflater;

    public M007_1_FeedAdapter(ArrayList<RSS> listRSS, Context mContext) {
        this.listRSS= listRSS;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public M007_1_FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.m007_tem_row_tintuc,parent,false);
        return new M007_1_FeedViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(M007_1_FeedViewHolder holder, int position) {

        holder.txtTitle.setText(listRSS.get(position).getTitle());
        holder.txtPubDate.setText(listRSS.get(position).getDate());
        if (listRSS.get(position).getThumnail().isEmpty()){
            holder.iv.setImageResource(R.drawable.ic_baseline_fireplace_24);
        } else{
            Picasso.with(mContext).load(listRSS.get(position).getThumnail()).into(holder.iv);
        }



        holder.setItemClickListener(new M007_ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick)
                {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(listRSS.get(position).getUrl()));
                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRSS.size();
    }
}