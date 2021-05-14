package com.example.iheath.entities.RoomDatabase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "calo" ,primaryKeys = {"id"})
public class Calo implements Serializable {

    @NonNull
    @ColumnInfo(name="id")
    private int id;


    @NonNull
    @ColumnInfo(name="ngay")
    private String ngay;

    @NonNull
    @ColumnInfo(name="calo")
    private Double calo;

    @NonNull
    @ColumnInfo(name="gioitinh")
    private String gioiTinhUser;

    @NonNull
    @ColumnInfo(name="cuogndovandong")
    private String cuongDoHoatDongUser;

    @NonNull
    @ColumnInfo(name="tuoi")
    private int tuoiUser;

    @NonNull
    @ColumnInfo(name="chieucao")
    private Double chieuCaoUser;

    @NonNull
    @ColumnInfo(name="cannang")
    private double canNangUser;

    @NonNull
    @ColumnInfo(name="ten")
    private String ten;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNgay() {
        return ngay;
    }

    public void setNgay(@NonNull String ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "Calo{" +
                "id=" + id +
                ", ngay='" + ngay + '\'' +
                ", calo=" + calo +
                ", gioiTinhUser='" + gioiTinhUser + '\'' +
                ", cuongDoHoatDongUser='" + cuongDoHoatDongUser + '\'' +
                ", tuoiUser=" + tuoiUser +
                ", chieuCaoUser=" + chieuCaoUser +
                ", canNangUser=" + canNangUser +
                ", ten='" + ten + '\'' +
                '}';
    }

    @NonNull
    public String getTen() {
        return ten;
    }

    public void setTen(@NonNull String ten) {
        this.ten = ten;
    }

    public Calo(int id, @NonNull String ngay, @NonNull Double calo, @NonNull String gioiTinhUser, @NonNull String cuongDoHoatDongUser, int tuoiUser, @NonNull Double chieuCaoUser, double canNangUser, @NonNull String ten) {
        this.id = id;
        this.ngay = ngay;
        this.calo = calo;
        this.gioiTinhUser = gioiTinhUser;
        this.cuongDoHoatDongUser = cuongDoHoatDongUser;
        this.tuoiUser = tuoiUser;
        this.chieuCaoUser = chieuCaoUser;
        this.canNangUser = canNangUser;
        this.ten = ten;
    }

    @NonNull
    public Double getCalo() {
        return calo;
    }

    public void setCalo(@NonNull Double calo) {
        this.calo = calo;
    }

    @NonNull
    public String getGioiTinhUser() {
        return gioiTinhUser;
    }

    public void setGioiTinhUser(@NonNull String gioiTinhUser) {
        this.gioiTinhUser = gioiTinhUser;
    }

    @NonNull
    public String getCuongDoHoatDongUser() {
        return cuongDoHoatDongUser;
    }

    public void setCuongDoHoatDongUser(@NonNull String cuongDoHoatDongUser) {
        this.cuongDoHoatDongUser = cuongDoHoatDongUser;
    }

    public int getTuoiUser() {
        return tuoiUser;
    }

    public void setTuoiUser(int tuoiUser) {
        this.tuoiUser = tuoiUser;
    }

    @NonNull
    public Double getChieuCaoUser() {
        return chieuCaoUser;
    }

    public void setChieuCaoUser(@NonNull Double chieuCaoUser) {
        this.chieuCaoUser = chieuCaoUser;
    }

    public double getCanNangUser() {
        return canNangUser;
    }

    public void setCanNangUser(double canNangUser) {
        this.canNangUser = canNangUser;
    }
}
