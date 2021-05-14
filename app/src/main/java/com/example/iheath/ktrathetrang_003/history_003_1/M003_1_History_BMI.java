package com.example.iheath.ktrathetrang_003.history_003_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iheath.R;
import com.example.iheath.adapter.BMI_ListviewAdapter_M003_1;
import com.example.iheath.entities.RoomDatabase.BMI;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.util.ArrayList;

public class M003_1_History_BMI extends AppCompatActivity {
    BMI_ListviewAdapter_M003_1 bmi_listviewAdapter;
    ListView listViewBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_1_history_bmi);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Lịch sử hoạt động");
        actionBar.setDisplayHomeAsUpEnabled(true);

       loadHistory();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.iv_delete_history_m003:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Cảnh báo!");
                dialog.setMessage("Bạn có chắc muốn xóa lịch sử BMI?");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserManager.getInstance().xoaBMI(data -> {
                        });
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete_history_m003,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void loadHistory(){
        UserManager.getInstance().getAllBMI(data->{
            runOnUiThread(()->{
                ArrayList<BMI> bmis= (ArrayList<BMI>) data;
                ArrayList<BMI> list= new ArrayList<>();
                for(int i= bmis.size()-1;i>=0;i--){
                    list.add(bmis.get(i));
                }
                bmi_listviewAdapter= new BMI_ListviewAdapter_M003_1(list);
                listViewBMI= findViewById(R.id.lv_history_bmi_003);
                listViewBMI.setAdapter(bmi_listviewAdapter);
            });
        });
    }
}
