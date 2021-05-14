package com.example.iheath.entities;

public class TinhToan {
    public TinhToan() {
    }

    public double tinhCaloTieuThu(double chieuCao, double canNang, int tuoi, String sex, String muc){
        double  TDEE = 0,mucVanDong = 0;
        double age= (double) tuoi;
        if(muc.equals("ít")){
            mucVanDong = 1.2;
        }else if(muc.equals("nhẹ")){
            mucVanDong= 1.375;
        }else if(muc.equals("vừa")){
            mucVanDong= 1.55;
        }else if(muc.equals("nặng")){
            mucVanDong= 1.725;
        }else if(muc.equals("rất nặng")){
            mucVanDong= 1.9;
        }
        if(sex.equals("nam")){
            TDEE= (13.397*canNang+4.799*chieuCao-5.677*tuoi+88.362)* mucVanDong;
        }else if(sex.equals("nữ")){
            TDEE= mucVanDong*(9.247*canNang+3.098*chieuCao-4.330*tuoi+447.593);
        }
        return TDEE;
    }
}
