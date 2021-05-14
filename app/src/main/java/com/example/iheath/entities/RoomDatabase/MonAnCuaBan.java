package com.example.iheath.entities.RoomDatabase;

import com.example.iheath.entities.ThucPhamCanTinhCalo;

import java.io.Serializable;
import java.util.ArrayList;

public class MonAnCuaBan implements Serializable {
    private String tenMonAn;
    private Double luongCalo;
    private ArrayList<ThucPhamCanTinhCalo> thucPhamCanTinhCalo= new ArrayList<>();

    public MonAnCuaBan(String tenMonAn, Double luongCalo, ArrayList<ThucPhamCanTinhCalo> thucPhamCanTinhCalo) {
        this.tenMonAn = tenMonAn;
        this.luongCalo = luongCalo;
        this.thucPhamCanTinhCalo = thucPhamCanTinhCalo;
    }

    public MonAnCuaBan() {
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public Double getLuongCalo() {
        return luongCalo;
    }

    public void setLuongCalo(Double luongCalo) {
        this.luongCalo = luongCalo;
    }


    public ArrayList<ThucPhamCanTinhCalo> getThucPhamCanTinhCalo() {
        return thucPhamCanTinhCalo;
    }

    public void setThucPhamCanTinhCalo(ArrayList<ThucPhamCanTinhCalo> thucPhamCanTinhCalo) {
        this.thucPhamCanTinhCalo = thucPhamCanTinhCalo;
    }

    @Override
    public String toString() {
        return "MonAnCuaBan{" +
                "tenMonAn='" + tenMonAn + '\'' +
                ", luongCalo=" + luongCalo +
                ", thucPhamCanTinhCalo=" + thucPhamCanTinhCalo +
                '}';
    }
}
