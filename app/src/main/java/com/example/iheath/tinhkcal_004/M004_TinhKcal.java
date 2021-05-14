package com.example.iheath.tinhkcal_004;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//
//
import com.example.iheath.R;
import com.example.iheath.adapter.M004_AutoCompleteCountryAdapter;
import com.example.iheath.entities.RoomDatabase.MonAn;
import com.example.iheath.entities.RoomDatabase.MonAnCuaBan;
import com.example.iheath.entities.RoomDatabase.ThanhPhanChiTiet;
import com.example.iheath.entities.RoomDatabase.UserManager;
import com.example.iheath.entities.ThucPham;
import com.example.iheath.entities.ThucPhamCanTinhCalo;
//
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class M004_TinhKcal extends AppCompatActivity implements View.OnClickListener {
    public final static String KEY_CHI_TIET_MON_AN="chitietmonan";
    EditText  etSoLuong;
    M004_AutoCompleteCountryAdapter adapter;
    AutoCompleteTextView etTenSanPham;
    TextView tvCalo, tvKetQua;
    EditText etTenMonAn;
    LinearLayout layoutList;
    Button buttonAdd, btChiTiet;
    Button buttonSubmitList;
    List<ThucPham> listThucPham;

    ArrayList<ThucPhamCanTinhCalo> listThucPhamCanTinhCalo = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m004_tinhkcal);

        Intent intent= getIntent();
        listThucPham= (List<ThucPham>) intent.getSerializableExtra("TAG_M001");

        adapter = new M004_AutoCompleteCountryAdapter(this, listThucPham);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tính Calo");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubmitList = findViewById(R.id.button_submit_list);

        buttonAdd.setOnClickListener(this);
        buttonSubmitList.setOnClickListener(this);

        btChiTiet= findViewById(R.id.bt_ChiTiet);
        btChiTiet.setOnClickListener(this);

        etTenMonAn=findViewById(R.id.et_tenMonAn_004);

        tvKetQua= findViewById(R.id.tv_ketQua_004);
        addView();
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_add) {
           if(etSoLuong.getText()==null||etSoLuong.getText().equals("0")||tvCalo.getText().equals("0 calo")){
                Toast.makeText(this,"Bạn chưa nhập số lượng hoặc tên thành phần không đúng!", Toast.LENGTH_SHORT).show();
           }else {
               etSoLuong.setInputType(InputType.TYPE_NULL);
               etSoLuong.setFocusable(false);
               etTenSanPham.setInputType(InputType.TYPE_NULL);
               etTenSanPham.setFocusable(false);
               addView();
           }
        } else if (v.getId() == R.id.button_submit_list){
            if(etSoLuong.getText()==null||etSoLuong.getText().equals("0")||tvCalo.getText().equals("0 calo")){
                Toast.makeText(this,"Bạn chưa nhập số lượng hoặc tên thành phần không đúng!", Toast.LENGTH_SHORT).show();
            }else {
                double ketQua = 0;
                String s="";
                checkIfValidAndRead();
                for (int k = 0; k < listThucPhamCanTinhCalo.size(); k++) {
                    ketQua = ketQua + listThucPhamCanTinhCalo.get(k).getCalo();
//                    s=s+ listThucPhamCanTinhCalo.get(k).getCalo()+" ";
                }
//                Toast.makeText(M004_TinhKcal.this, s, Toast.LENGTH_SHORT).show();
                DecimalFormat f = new DecimalFormat("##.##");
                tvKetQua.setText(f.format(ketQua).replace(",",".")+"");
            }
        }else if(v.getId()==R.id.bt_ChiTiet){
            if(etTenMonAn.getText().equals("")||etTenMonAn.getText()==null){
                Toast.makeText(this,"Bạn chưa nhập tên món ăn!", Toast.LENGTH_SHORT).show();
            }
            else{
                if(Double.parseDouble(tvKetQua.getText().toString())!=0) {
                    MonAnCuaBan monAnCuaBan = new MonAnCuaBan(etTenMonAn.getText().toString(), Double.parseDouble(tvKetQua.getText().toString()), listThucPhamCanTinhCalo);
                    Intent intent = new Intent();
                    intent.putExtra(KEY_CHI_TIET_MON_AN, monAnCuaBan);
                    intent.setClass(this, M004_1_ThongTinMonAn.class);
                    startActivity(intent);

                    UserManager.getInstance().getAllMonAn(data -> {
                        runOnUiThread(() -> {
                            List<MonAn> list1 = (List<MonAn>) data;
                            int xl;
                            if (list1.size() == 0) {
                                xl = 0;
                            } else {
                                xl = list1.get(list1.size() - 1).getId();
                            }
                            MonAn monAn = new MonAn(xl + 1, monAnCuaBan.getTenMonAn(), monAnCuaBan.getLuongCalo());
                            UserManager.getInstance().themMonAn(data1 -> {
                            }, monAn);
                        });
                    });


                    List<ThucPhamCanTinhCalo> thucPhamCanTinhCalos= monAnCuaBan.getThucPhamCanTinhCalo();
                    UserManager.getInstance().getAllThanhPhan(data -> {
                        List<ThanhPhanChiTiet> list= (List<ThanhPhanChiTiet>) data;
                        int dem=list.size();
                        for(int i=0;i<thucPhamCanTinhCalos.size();i++){
                            int id= dem+i;
                            ThanhPhanChiTiet thanhPhanChiTiet= new ThanhPhanChiTiet(id, thucPhamCanTinhCalos.get(i).getTenSanPham(),thucPhamCanTinhCalos.get(i).getSoLuong(), thucPhamCanTinhCalos.get(i).getCalo(), monAnCuaBan.getTenMonAn());
                            Log.i("TAGBRO", thanhPhanChiTiet.toString());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UserManager.getInstance().themThanhPhan(data -> {

                                    }, thanhPhanChiTiet);
                                }
                            });
                        }
                    });
                }
            }
        }

    }

    private void checkIfValidAndRead() {
        Intent intent= getIntent();
        listThucPham= (List<ThucPham>) intent.getSerializableExtra("TAG_M001");
        listThucPhamCanTinhCalo.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View view = layoutList.getChildAt(i);

            etTenSanPham= view.findViewById(R.id.et_TenSanPham);
            etSoLuong= view.findViewById(R.id.et_SoLuong);
            tvCalo= view.findViewById(R.id.tv_Calo);

            ThucPhamCanTinhCalo thucPhamCanTinhCalo= new ThucPhamCanTinhCalo();

            thucPhamCanTinhCalo.setTenSanPham(etTenSanPham.getText().toString());
            thucPhamCanTinhCalo.setSoLuong(Double.parseDouble(etSoLuong.getText().toString()));
            thucPhamCanTinhCalo.setCalo(Double.parseDouble(tvCalo.getText().toString().replace(',','.')));
            listThucPhamCanTinhCalo.add(thucPhamCanTinhCalo);
        }

        if(listThucPhamCanTinhCalo.size()==0){
            result = false;
            Toast.makeText(this, "Add Cricketers First!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(this, "Enter All Details Correctly!", Toast.LENGTH_SHORT).show();
        }


    }

    private void addView() {
        Intent intent= getIntent();
        listThucPham= (List<ThucPham>) intent.getSerializableExtra("TAG_M001");
        final View cricketerView = getLayoutInflater().inflate(R.layout.m004_item_thucpham,null,false);

        etTenSanPham = cricketerView.findViewById(R.id.et_TenSanPham);
        etTenSanPham.setAdapter(adapter);
        etSoLuong= cricketerView.findViewById(R.id.et_SoLuong);
        tvCalo=cricketerView.findViewById(R.id.tv_Calo);
        etSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(etSoLuong.getText().toString().isEmpty()==false){
                    for(int j=0;j<listThucPham.size();j++){
                        if(etTenSanPham.getText().toString().equals(listThucPham.get(j).getName())){
                            DecimalFormat f = new DecimalFormat("##.##");
                             tvCalo.setText(f.format(Double.parseDouble(etSoLuong.getText().toString())*listThucPham.get(j).getNangluong()/100)+"");
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ImageView imageClose = (ImageView)cricketerView.findViewById(R.id.image_remove);


        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(M004_TinhKcal.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn xóa thực phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeView(cricketerView);
                    }
                });
               builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
               });
               AlertDialog alertDialog = builder.create();
               alertDialog.show();
            }
        });

        layoutList.addView(cricketerView);

    }

    private void removeView(View view){

        layoutList.removeView(view);

    }

}
