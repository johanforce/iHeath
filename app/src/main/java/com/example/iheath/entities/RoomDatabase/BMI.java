package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "bmi" ,primaryKeys = {"id"})
public class BMI implements Serializable {

    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    @ColumnInfo(name="ngay")
    private String ngay;

    @NonNull
    @ColumnInfo(name="bmi")
    private Double bmi;

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

    @NonNull
    public Double getBmi() {
        return bmi;
    }

    public void setBmi(@NonNull Double bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "BMI{" +
                "id=" + id +
                ", ngay='" + ngay + '\'' +
                ", bmi=" + bmi +
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

    public BMI(int id, @NonNull String ngay, @NonNull Double bmi, @NonNull Double chieuCaoUser, double canNangUser, @NonNull String ten) {
        this.id = id;
        this.ngay = ngay;
        this.bmi = bmi;
        this.chieuCaoUser = chieuCaoUser;
        this.canNangUser = canNangUser;
        this.ten = ten;
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
