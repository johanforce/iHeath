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
import com.example.iheath.entities.RoomDatabase.BMI;
import com.example.iheath.entities.RoomDatabase.UserManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Frg003_BMI extends Fragment implements View.OnClickListener {
    private EditText etChieuCao, etCanNang;
    private TextView tvKetQua;
    private Button btKetQua;
    double bmi=-1;
    DecimalFormat f;
    public Frg003_BMI() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m003_frg_bmi, container, false);
        setHasOptionsMenu(true);
        etCanNang= view.findViewById(R.id.etCanNang_BMI003);
        etChieuCao=view.findViewById(R.id.etChieuCao_BMI003);
        tvKetQua=view.findViewById(R.id.tvKetQua_BMI003);
        tvKetQua.setOnClickListener(this);
        btKetQua=view.findViewById(R.id.btKetQua_BMI003);
        f = new DecimalFormat("##.#");
        btKetQua.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btKetQua_BMI003){
            double h= (Double.parseDouble(etChieuCao.getText().toString()))/100;
            double m= Double.parseDouble(etCanNang.getText().toString());
            bmi= m/(h*h);

            tvKetQua.setText("Chỉ số BMI của bạn là: "+ f.format(bmi)+". Với chỉ số này bạn đang ở trạng thái "+ ketQuaBMI(bmi));
        }
    }

    public String ketQuaBMI(double bmi){
        String s="";
        if(bmi<=18.5){
            s="gầy";
        }else if(bmi>18.5&& bmi<=24.9){
            s="bình thường";
        }else if(bmi==25){
            s="thừa cân";
        }else if(bmi>=25.1&& bmi<=29.9){
            s="tiền béo phì";
        }else if(bmi>=30&&bmi<=34.9){
            s="béo phì độ I";
        }
        else if(bmi>=35&& bmi<=39.9){
            s="béo phì độ II";
        }else if(bmi>=40){
            s="béo phì độ III";
        }
        return s;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.iv_additem){
           if(etCanNang.getText().toString().length()==0||etChieuCao.getText().toString().length()==0||bmi==-1){
               Toast.makeText(getContext(),"Ban chua nhap day du thong tin", Toast.LENGTH_SHORT).show();
           }if(etCanNang.getText().toString().length()>0&&etChieuCao.getText().toString().length()>0&&bmi!=-1) {
               AlertDialog.Builder dialog= new AlertDialog.Builder(getActivity());
               View view= getLayoutInflater().inflate(R.layout.m003_add_item,null);
               dialog.setView(view);
               dialog.setTitle("Luu du lieu");
               dialog.setMessage("Xin moi ban nhap ten:");
               dialog.setPositiveButton("Luu", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       EditText editText= view.findViewById(R.id.et_ten);
                       Calendar calendar= Calendar.getInstance();
                       SimpleDateFormat dateFormat;
                       dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                       String date = dateFormat.format(calendar.getTime());
                       UserManager.getInstance().getAllBMI(data -> {
                           getActivity().runOnUiThread(()->{
                               List<BMI> list1 = (List<BMI>) data;
                               int xl;
                               if(list1.size()==0){
                                   xl=1;
                               }
                               else
                                   xl = list1.get(list1.size()-1).getId();
                               double chiSoBMI= Double.parseDouble(f.format(bmi).replace(",","."));
                               BMI bmi1= new BMI(xl+1, date,chiSoBMI,Double.parseDouble(etChieuCao.getText().toString()),Double.parseDouble(etCanNang.getText().toString()),editText.getText().toString());
                               UserManager.getInstance().themBMI(data1 -> {
                               }, bmi1);
                           });
                       });
                       Toast.makeText(getContext(), "Them thanh cong!", Toast.LENGTH_SHORT).show();
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
