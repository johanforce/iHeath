package com.example.iheath.tintuc_007.danhsachchitiet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;

import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.RSS;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.example.iheath.adapter.M007_1_FeedAdapter;
import com.example.iheath.adapter.M007_AutoCompleteSearchAdapter;
import com.example.iheath.tintuc_007.M007_TinTuc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class M007_1_DanhSachTinTuc extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rc_News;
    private FrameLayout flSearch;
    private M007_AutoCompleteSearchAdapter adapter;
    private AutoCompleteTextView autoSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m007_danh_sach_tin_tuc);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tin tức");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        initView();
    }

    private void initView() {
        autoSearch= findViewById(R.id.et_search_m007_1);
        flSearch= findViewById(R.id.fl_search_m007_1);
        flSearch.setOnClickListener(this);
        rc_News=findViewById(R.id.recyclerViewNews);
        showTinTuc();
        timKiem();
    }
    private void showTinTuc(){
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        rc_News.setLayoutManager(linearLayoutManager);
        UserManager.getInstance().getAllRSS(data -> {
            ArrayList<RSS> list= (ArrayList<RSS>) data;
//            ArrayList<RSS> list2= new ArrayList<>();
//            for(int i=list.size()-1;i>=0;i--){
//                list2.add(list.get(i));
//            }

            M007_1_FeedAdapter adapter= new M007_1_FeedAdapter(sort(list),M007_1_DanhSachTinTuc.this);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rc_News.setAdapter(adapter);
                }
            });
        });
    }
    private ArrayList<RSS> sort(ArrayList<RSS> list){
        String pattern = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Collections.sort(list, new Comparator<RSS>() {
            @Override
            public int compare(RSS o1, RSS o2) {
                if(o1.getDate().compareTo(o2.getDate())<0){
                    return 1;
                }else {
                    return -1;
                }
            }
        });
        return list;
    }
    private void timKiem(){
        UserManager.getInstance().getAllRSS(data -> {
            List<RSS> list= (List<RSS>) data;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter= new M007_AutoCompleteSearchAdapter(M007_1_DanhSachTinTuc.this, list);
                    autoSearch.setAdapter(adapter);
                }
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent= new Intent();
                intent.setClass(M007_1_DanhSachTinTuc.this, M007_TinTuc.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fl_search_m007_1){
            UserManager.getInstance().getAllRSS(data -> {
                List<RSS> list= (List<RSS>) data;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<list.size();i++){
                            if(autoSearch.getText().toString().equals(list.get(i).getTitle())){
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(i).getUrl()));
                                    browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(browserIntent);
//                                Intent intent= new Intent();
//                                intent.putExtra("URL", list.get(i).getUrl());
//                                intent.setClass(M001_MenuLeague.this, M003_WebView.class);
//                                startActivity(intent);
                            }
                        }
                    }
                });
            });
        }
    }
}