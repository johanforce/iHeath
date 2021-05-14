package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.iheath.entities.TinhToan;

import java.io.Serializable;

@Entity(tableName = "HoSo" ,primaryKeys = {"idUser"})
public class User implements Serializable {

    @NonNull
    @ColumnInfo(name="idUser")
    private int idUser;

    @NonNull
    @ColumnInfo(name="tenUser")
    private String tenUser;

    @NonNull
    @ColumnInfo(name="gioiTinhUser")
    private String gioiTinhUser;

    @NonNull
    @ColumnInfo(name="cuongDoHoatDong")
    private String cuongDoHoatDongUser;

    @NonNull
    @ColumnInfo(name="tuoiUser")
    private int tuoiUser;

    @NonNull
    @ColumnInfo(name="chieuCaoUser")
    private Double chieuCaoUser;

    @NonNull
    @ColumnInfo(name="canNangUser")
    private double canNangUser;

    @NonNull
    @ColumnInfo(name="canNangMuonGiam")
    private Double canNangMuonGiam;

    @NonNull
    @ColumnInfo(name="soTuanThucHien")
    private int soTuanThucHien;

    @ColumnInfo(name="caloUser")
    private double caloUser;
    public User(int idUser, String tenUser, String gioiTinhUser, String cuongDoHoatDongUser, int tuoiUser, Double chieuCaoUser, Double canNangUser, double canNangMuonGiam, int soTuanThucHien) {
        this.idUser=idUser;
        this.tenUser = tenUser;
        this.gioiTinhUser = gioiTinhUser;
        this.cuongDoHoatDongUser = cuongDoHoatDongUser;
        this.tuoiUser = tuoiUser;
        this.chieuCaoUser = chieuCaoUser;
        this.canNangUser = canNangUser;
        this.canNangMuonGiam= canNangMuonGiam;
        this.soTuanThucHien=soTuanThucHien;
        TinhToan toan= new TinhToan();
        caloUser=toan.tinhCaloTieuThu(chieuCaoUser,canNangUser,tuoiUser,gioiTinhUser,cuongDoHoatDongUser);
//        caloUser=chieuCaoUser*2;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(@NonNull String tenUser) {
        this.tenUser = tenUser;
    }

    public String getGioiTinhUser() {
        return gioiTinhUser;
    }

    public void setGioiTinhUser(@NonNull String gioiTinhUser) {
        this.gioiTinhUser = gioiTinhUser;
    }

    public String getCuongDoHoatDongUser() {
        return cuongDoHoatDongUser;
    }

    public void setCuongDoHoatDongUser(@NonNull String cuongDoHoatDongUser) {
        this.cuongDoHoatDongUser = cuongDoHoatDongUser;
    }

    public int getTuoiUser() {
        return tuoiUser;
    }

    public void setTuoiUser(@NonNull  int tuoiUser) {
        this.tuoiUser = tuoiUser;
    }

    public Double getChieuCaoUser() {
        return chieuCaoUser;
    }

    public void setChieuCaoUser(@NonNull Double chieuCaoUser) {
        this.chieuCaoUser = chieuCaoUser;
    }

    public Double getCanNangUser() {
        return canNangUser;
    }

    public void setCanNangUser(@NonNull Double canNangUser) {
        this.canNangUser = canNangUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setCanNangUser(@NonNull  double canNangUser) {
        this.canNangUser = canNangUser;
    }

    public void setIdUser(@NonNull  int idUser) {
        this.idUser = idUser;
    }

    @NonNull
    public Double getCanNangMuonGiam() {
        return canNangMuonGiam;
    }

    public void setCanNangMuonGiam(@NonNull Double canNangMuonGiam) {
        this.canNangMuonGiam = canNangMuonGiam;
    }

    public int getSoTuanThucHien() {
        return soTuanThucHien;
    }

    public void setSoTuanThucHien(int soTuanThucHien) {
        this.soTuanThucHien = soTuanThucHien;
    }

    public double getCaloUser() {
        return caloUser;
    }

    public void setCaloUser(double caloUser) {
        this.caloUser = caloUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", tenUser='" + tenUser + '\'' +
                ", gioiTinhUser='" + gioiTinhUser + '\'' +
                ", cuongDoHoatDongUser='" + cuongDoHoatDongUser + '\'' +
                ", tuoiUser=" + tuoiUser +
                ", chieuCaoUser=" + chieuCaoUser +
                ", canNangUser=" + canNangUser +
                ", canNangMuonGiam=" + canNangMuonGiam +
                ", soTuanThucHien=" + soTuanThucHien +
                ", caloUser=" + caloUser +
                '}';
    }


}
