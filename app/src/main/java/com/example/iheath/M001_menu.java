package com.example.iheath;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.iheath.entities.ThucPham;
import com.example.iheath.ktrathetrang_003.M003_KtraTheTrang;
import com.example.iheath.monancuaban_006.M006_MonAnUaThich;
import com.example.iheath.nhiptim_008.M008_NhipTim;
import com.example.iheath.theodoisuckhoe_005.M005_DanhSachNguoiDung;
import com.example.iheath.thucpham_002.M002_ThucPham;
import com.example.iheath.tinhkcal_004.M004_TinhKcal;
import com.example.iheath.tintuc_007.M007_TinTuc;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.example.iheath.R;

public class M001_menu extends AppCompatActivity implements View.OnClickListener{
    private List<ThucPham> groups= new ArrayList<>();
    private CardView cvFood, cvChiSo, cvTinhCalo, cvTheoDoi, cvTinTuc, cvMonAn, cvNhipTim;
    TextView tvCity, tvDay, tvStatus, tvHumidity, tvTemp;
    ImageView ivWeather, ivInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_menu);
        initViews();
        getData();

        OkHttpClient client = new OkHttpClient();
        // Khởi tạo Moshi adapter để biến đổi json sang model java (ở đây là User)
        Moshi moshi = new Moshi.Builder().build();
        Type usersType = Types.newParameterizedType(List.class, ThucPham.class);
        final JsonAdapter<List<ThucPham>> jsonAdapter = moshi.adapter(usersType);

        // Tạo request lên server.

        Request request = new Request.Builder()
                .url("https://calloappcs.herokuapp.com/listfood")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                groups= jsonAdapter.fromJson(json);

                cvFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProgressDialog mDialog = new ProgressDialog(M001_menu.this);
                        mDialog.setMessage("Please wait...");
                        mDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent= new Intent();
                                intent.putExtra("TAG", (Serializable) groups);
                                intent.setClass(M001_menu.this, M002_ThucPham.class);
                                startActivity(intent);
                                mDialog.dismiss();
                            }
                            int x=0;
                        },2000);
                    }
                });
                cvTinhCalo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ProgressDialog mDialog = new ProgressDialog(M001_menu.this);
                        mDialog.setMessage("Please wait...");
                        mDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent= new Intent();
                                intent.putExtra("TAG_M001", (Serializable) groups);
                                intent.setClass(M001_menu.this, M004_TinhKcal.class);
                                startActivity(intent);
                                mDialog.dismiss();
                            }
                            int x=0;
                        },2000);
                    }
                });
            }
        });

    }




    private void getData() {
        tvCity= findViewById(R.id.txtCity);
        tvDay= findViewById(R.id.txtDay);
        ivWeather= findViewById(R.id.ivWeather);
        tvStatus=findViewById(R.id.txtStatus);
        tvHumidity=findViewById(R.id.txtHumidity);
        tvTemp=findViewById(R.id.txtTemp);

        OkHttpClient client = new OkHttpClient();
        // Tạo request lên server.
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=HaNoi&appid=51515b924739bf61ea8c4e53b39ee38c")
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response1) throws IOException {

                try {
                    String response = response1.body().string();
                    JSONObject jsonObject= new JSONObject(response);
                    String day= jsonObject.getString("dt");
                    String name= jsonObject.getString("name");


                    long l= Long.valueOf(day);
                    Date date= new Date(l*1000L);
                    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE, yyyy-MM-dd");
                    String Day= simpleDateFormat.format(date);



                    JSONArray jsonArrayWeather= jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather= jsonArrayWeather.getJSONObject(0);
                    String status= jsonObjectWeather.getString("main");
                    String icon=jsonObjectWeather.getString("icon");

                    Handler uiHandler = new Handler(Looper.getMainLooper());
                    uiHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            Picasso.with(M001_menu.this).load("https://openweathermap.org/img/w/"+icon+".png").into(ivWeather);
                        }
                    });

                    JSONObject jsonObjectMain= jsonObject.getJSONObject("main");
                    String nhietDo= jsonObjectMain.getString("temp");
                    String doAm= jsonObjectMain.getString("humidity");
                    Double a= Double.valueOf(nhietDo);
                    String NhietDo= String.valueOf(a.intValue()- 273);



                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String day;
                            tvCity.setText(name);
                            String xday="";
                            if(status.equals("Clouds")){
                                xday="Có mây";
                            }
                            if(status.equals("Hazze")){
                                xday="Sương mỏng";
                            }
                            if(status.equals("Mist")){
                                xday="Sương mù";
                            }
                            if(status.equals("Fog")){
                                xday="Có mây";
                            }
                            if(status.equals("Clear")){
                                xday="Trong xanh";
                            }
                            if(status.equals("Rain")){
                                xday="Mưa";
                            }
                            if(status.equals("Snow")){
                                xday="Tuyết";
                            }
                            tvStatus.setText(xday);
                            tvDay.setText(Day);
                            tvHumidity.setText(doAm+"%");
                            tvTemp.setText(NhietDo+"°C");
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initViews() {
        cvFood= findViewById(R.id.cv_food_M001);
        cvFood.setOnClickListener(this);
        cvTheoDoi=findViewById(R.id.cv_TheoDoiCanNang_M001);
        cvTheoDoi.setOnClickListener(this);
        cvChiSo=findViewById(R.id.cv_chiSoCoThe_M001);
        cvChiSo.setOnClickListener(this);
        cvTinhCalo=findViewById(R.id.cv_tinhCalo_M001);
        cvTinhCalo.setOnClickListener(this);
        cvTinTuc=findViewById(R.id.cv_tinTuc_M007);
        cvTinTuc.setOnClickListener(this);
        cvMonAn=findViewById(R.id.cv_monAN_M006);
        cvMonAn.setOnClickListener(this);
        cvNhipTim= findViewById(R.id.cv_nhipTim_M008);
        cvNhipTim.setOnClickListener(this);

        ivInfo=findViewById(R.id.iv_info_m009);
        ivInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.cv_chiSoCoThe_M001){
            Intent intent = new Intent();
            intent.setClass(M001_menu.this, M003_KtraTheTrang.class);
            startActivity(intent);
        }else if(v.getId()==R.id.cv_TheoDoiCanNang_M001) {
            Intent intent = new Intent();
            intent.setClass(M001_menu.this, M005_DanhSachNguoiDung.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.cv_tinTuc_M007) {
            Intent intent = new Intent();
            intent.setClass(M001_menu.this, M007_TinTuc.class);
            startActivity(intent);
        }else if(v.getId()==R.id.cv_monAN_M006){
            Intent intent= new Intent();
            intent.setClass(M001_menu.this, M006_MonAnUaThich.class);
            startActivity(intent);
        }else if(v.getId()==R.id.cv_nhipTim_M008){
            Intent intent= new Intent();
            intent.setClass(M001_menu.this, M008_NhipTim.class);
            startActivity(intent);
        }else if(v.getId()==R.id.iv_info_m009){
            Intent intent= new Intent();
            intent.setClass(M001_menu.this, M009_Info.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Bạn muốn thoát chương trình?");
        dialog.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        dialog.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }
}