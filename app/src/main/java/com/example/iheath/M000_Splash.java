package com.example.iheath;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iheath.entities.ThucPham;
import com.example.iheath.thucpham_002.M002_ThucPham;
import com.example.iheath.tinhkcal_004.M004_TinhKcal;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class M000_Splash extends AppCompatActivity {
    private List<ThucPham> groups= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m000_splash_activity);
        initViews();
    }

    private void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(M000_Splash.this, M001_menu.class);
                startActivity(intent);
            }
            int x=0;
        },2000);
    }


//    private void loadAPI(){
//        OkHttpClient client = new OkHttpClient();
//
//        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
//        Moshi moshi = new Moshi.Builder().build();
//        Type usersType = Types.newParameterizedType(List.class, ThucPham.class);
//        final JsonAdapter<List<ThucPham>> jsonAdapter = moshi.adapter(usersType);
//
//        // Tạo request lên server.
//        Request request = new Request.Builder()
//                .url("https://calloappcs.herokuapp.com/listfood")
//                .build();
//
//        // Thực thi request.
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("Error", "Network Error");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
//                String json = response.body().string();
//                groups= jsonAdapter.fromJson(json);
//                        Intent intent= new Intent();
//                        intent.putExtra("TAG", (Serializable) groups);
//                        intent.setClass(M000_Splash.this, M001_menu.class);
//                        startActivity(intent);
//            }
//        });
//    }
}
