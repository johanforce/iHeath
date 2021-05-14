package com.example.iheath.entities.RoomDatabase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "calocannap" ,primaryKeys = {"id"})
public class CaloCanNap implements Serializable {

    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="ngay")
    private String ngay;

    @NonNull
    @ColumnInfo(name="calocannap")
    private Double caloCanNap;

    @NonNull
    @ColumnInfo(name="calohientai")
    private Double caloHienTai;

    @NonNull
    @ColumnInfo(name="sotuanthuchien")
    private Double soTuanThucHien;

    @NonNull
    @ColumnInfo(name="cannangmuongiam")
    private Double canNangMuonGiam;

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

    @NonNull
    public Double getCaloCanNap() {
        return caloCanNap;
    }

    public void setCaloCanNap(@NonNull Double caloCanNap) {
        this.caloCanNap = caloCanNap;
    }

    @Override
    public String toString() {
        return "CaloCanNap{" +
                "id=" + id +
                ", ngay='" + ngay + '\'' +
                ", caloCanNap=" + caloCanNap +
                ", caloHienTai=" + caloHienTai +
                ", soTuanThucHien=" + soTuanThucHien +
                ", canNangMuonGiam=" + canNangMuonGiam +
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

    public CaloCanNap(int id, @NonNull String ngay, @NonNull Double caloCanNap, @NonNull Double caloHienTai, @NonNull Double soTuanThucHien, @NonNull Double canNangMuonGiam, @NonNull String ten) {
        this.id = id;
        this.ngay = ngay;
        this.caloCanNap = caloCanNap;
        this.caloHienTai = caloHienTai;
        this.soTuanThucHien = soTuanThucHien;
        this.canNangMuonGiam = canNangMuonGiam;
        this.ten = ten;
    }

    @NonNull
    public Double getCaloHienTai() {
        return caloHienTai;
    }

    public void setCaloHienTai(@NonNull Double caloHienTai) {
        this.caloHienTai = caloHienTai;
    }

    @NonNull
    public Double getSoTuanThucHien() {
        return soTuanThucHien;
    }

    public void setSoTuanThucHien(@NonNull Double soTuanThucHien) {
        this.soTuanThucHien = soTuanThucHien;
    }

    @NonNull
    public Double getCanNangMuonGiam() {
        return canNangMuonGiam;
    }

    public void setCanNangMuonGiam(@NonNull Double canNangMuonGiam) {
        this.canNangMuonGiam = canNangMuonGiam;
    }

}
