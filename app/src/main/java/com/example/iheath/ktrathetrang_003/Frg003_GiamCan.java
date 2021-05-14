package com.example.iheath.ktrathetrang_003;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.iheath.R;
import com.example.iheath.entities.RoomDatabase.CaloCanNap;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Frg003_GiamCan extends Fragment implements View.OnClickListener {
    private final static Double CALO_TRONG_MOT_KG= 7716.17918;
    private EditText etCaloHienTai, etCanNangMuonGiam, etThoiGian;
    private TextView tvKetQua, tvAlert;
    private Button btKetQua;
    double soCaloCanTrongMotNgay=-1;
    DecimalFormat f;
    public Frg003_GiamCan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m003_frg_giam_can, container, false);
        setHasOptionsMenu(true);
        etCanNangMuonGiam= view.findViewById(R.id.etCanNang_GiamCan003);
        etCaloHienTai=view.findViewById(R.id.etCalo_GiamCan003);
        tvKetQua=view.findViewById(R.id.tvKetQua_GiamCan003);
        tvKetQua.setOnClickListener(this);
        btKetQua=view.findViewById(R.id.btKetQua_GiamCan003);
        btKetQua.setOnClickListener(this);
        etThoiGian=view.findViewById(R.id.etThoiGian_GiamCan003);
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btKetQua_GiamCan003){
            soCaloCanTrongMotNgay= Double.parseDouble(etCaloHienTai.getText().toString())-((Double.parseDouble(etCanNangMuonGiam.getText().toString())*CALO_TRONG_MOT_KG)/(Double.parseDouble(etThoiGian.getText().toString())*7));
            f = new DecimalFormat("##.##");

            tvKetQua.setText("Số calo bạn nạp trong một ngày để giảm "+ etCanNangMuonGiam.getText().toString()+" trong vòng "+etThoiGian.getText().toString()+" tuần là: "+ f.format(soCaloCanTrongMotNgay));
            if(soCaloCanTrongMotNgay<1200){
                tvKetQua.setText("Số calo trên của bạn đã nhỏ hơn lượng calo Bộ y tế khuyến cáo nên nạp vào trong ngày. Bạn nên sắp xếp lại lịch trình giảm cân cho phù hợp hơn!");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.iv_additem){
            if(etCaloHienTai.getText().toString().length()==0||etCanNangMuonGiam.getText().toString().length()==0||etThoiGian.getText().toString().length()==0||soCaloCanTrongMotNgay==-1){
                Toast.makeText(getContext(),"Ban chua nhap day du thong tin", Toast.LENGTH_SHORT).show();
            }else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                View view = getLayoutInflater().inflate(R.layout.m003_add_item, null);
                dialog.setView(view);
                dialog.setTitle("Luu du lieu");
                dialog.setMessage("Xin moi ban nhap ten:");
                dialog.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = view.findViewById(R.id.et_ten);
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat dateFormat;
                        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                        String date = dateFormat.format(calendar.getTime());
                        UserManager.getInstance().getAllCaloCanNap(data -> {
                            getActivity().runOnUiThread(() -> {
                                List<CaloCanNap> list1 = (List<CaloCanNap>) data;
                                int xl;
                                if (list1.size() == 0) {
                                    xl = 1;
                                } else
                                    xl = list1.get(list1.size() - 1).getId();
                                double chiSoCaloCanNhap= Double.parseDouble(f.format(soCaloCanTrongMotNgay).replace(",","."));
                                CaloCanNap caloCanNap = new CaloCanNap(xl + 1, date, chiSoCaloCanNhap, Double.parseDouble(etCaloHienTai.getText().toString()), Double.parseDouble(etThoiGian.getText().toString()), Double.parseDouble(etCanNangMuonGiam.getText().toString()), editText.getText().toString());
                                UserManager.getInstance().themCaloCanNap(data1 -> {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getActivity(), "Them thanh cong!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }, caloCanNap);
                            });
                        });
                    }
                });
                dialog.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
