package com.example.iheath.theodoisuckhoe_005;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iheath.R;
import com.example.iheath.adapter.M005_ListUserAdapter;
import com.example.iheath.entities.TinhToan;
import com.example.iheath.entities.RoomDatabase.User;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.text.DecimalFormat;

import static com.example.iheath.R.drawable.ic_female;
import static com.example.iheath.R.drawable.ic_man;

public class M005_2_ThongTinChiTiet extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_USER_CAN_NANG ="keyusercanang" ;
    TextView tvTenUser, tvTuoiUser, tvChieuCaoUser, tvCanNangUser, tvCuongdoUser, tvCalo;
    EditText etCanThua, etSoTuan;
    ImageView ivGioiTinhUser;
    Button btCapNhat;
    TinhToan toan= new TinhToan();
    User user, user2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m005_2_activity_thong_tin_chi_tiet);
        
        initView();
        thongTinNguoiDung();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chitiethoso,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuThucDon){

        }
        if(item.getItemId()==android.R.id.home){
            Intent intent= new Intent();
            intent.setClass(this, M005_DanhSachNguoiDung.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.menuTheoDoiCanNang){
            Intent intent= new Intent();
            if(user!=null){
                intent.putExtra(KEY_USER_CAN_NANG,user);
            }
            if(user2!=null){
                intent.putExtra(KEY_USER_CAN_NANG,user2);
            }
            intent.setClass(this, M005_3_TheoDoiCanNang.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin người dùng");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvCanNangUser=findViewById(R.id.tv_canNangNguoiDung);
        tvChieuCaoUser=findViewById(R.id.tv_chieuCaoNguoiDung);
        tvTenUser=findViewById(R.id.tv_tenNguoiDung);
        tvTuoiUser=findViewById(R.id.tv_tuoiNguoiDung);
        ivGioiTinhUser=findViewById(R.id.iv_gioiTinhNguoiDung);
        tvCuongdoUser=findViewById(R.id.tv_cuongDoNguoiDung);
        tvCalo=findViewById(R.id.tv_caloNguoiDung);

        etCanThua= findViewById(R.id.et_soCanNguoiDung);
        etSoTuan=findViewById(R.id.et_soTuanNguoiDung);

        btCapNhat=findViewById(R.id.bt_capNhatSoCanMuonGiam);
        btCapNhat.setOnClickListener(this);

    }

    public void thongTinNguoiDung(){
        Intent intent= getIntent();
        user= (User)intent.getSerializableExtra(M005_ListUserAdapter.KEY_HO_SO);
        user2= (User) intent.getSerializableExtra(M005_1_ThemHoSo.KEY_THEM_USER);
        if(user!=null){
            tvTenUser.setText(user.getTenUser());
            tvTuoiUser.setText(user.getTuoiUser()+"");
            tvCuongdoUser.setText(user.getCuongDoHoatDongUser());
            if(user.getGioiTinhUser().equals("nam")){
                ivGioiTinhUser.setImageResource(ic_man);
            }else {
                ivGioiTinhUser.setImageResource(ic_female);
            }
            tvChieuCaoUser.setText(user.getChieuCaoUser()+"");
            tvCanNangUser.setText(user.getCanNangUser()+"");
            tvCalo.setText(new DecimalFormat("#.###").format(user.getCaloUser())+"");
        }
        if(user2!=null){
            tvTenUser.setText(user2.getTenUser());
            tvTuoiUser.setText(user2.getTuoiUser()+"");
            tvCuongdoUser.setText(user2.getCuongDoHoatDongUser());
            if(user2.getGioiTinhUser().equals("nam")){
                ivGioiTinhUser.setImageResource(ic_man);
            }else {
                ivGioiTinhUser.setImageResource(ic_female);
            }
            tvChieuCaoUser.setText(user2.getChieuCaoUser()+"");
            tvCanNangUser.setText(user2.getCanNangUser()+"");
            tvCalo.setText(user2.getCaloUser()+"");
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_capNhatSoCanMuonGiam){
            user.setCanNangMuonGiam(Double.parseDouble(etCanThua.getText().toString()));
            user.setSoTuanThucHien(Integer.parseInt(etSoTuan.getText().toString()));
            UserManager.getInstance().suaUser(data -> {
                runOnUiThread(()->{
                });},user);
        }
    }
}