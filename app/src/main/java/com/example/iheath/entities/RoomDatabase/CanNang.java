package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "CanNang" ,primaryKeys = {"idCanNang"})
public class CanNang implements Serializable {
    @NonNull
    @ColumnInfo(name="idUser")
    private int idUser;

    @NonNull
    @ColumnInfo(name="ngay")
    private int ngay;

    @NonNull
    @ColumnInfo(name="canNang")
    private Double canNangUser;

    @NonNull
    @ColumnInfo(name="idCanNang")
    private int idCanNang;

    public CanNang(@NonNull int idCanNang,int idUser, int ngay, @NonNull Double canNangUser) {
        this.idUser = idUser;
        this.ngay = ngay;
        this.canNangUser = canNangUser;
        this.idCanNang = idCanNang;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    @NonNull
    public Double getCanNangUser() {
        return canNangUser;
    }

    public void setCanNangUser(@NonNull Double canNangUser) {
        this.canNangUser = canNangUser;
    }

    public int getIdCanNang() {
        return idCanNang;
    }

    public void setIdCanNang(int idCanNang) {
        this.idCanNang = idCanNang;
    }

    @Override
    public String toString() {
        return "CanNang{" +
                "idUser=" + idUser +
                ", ngay=" + ngay +
                ", canNangUser=" + canNangUser +
                ", idCanNang=" + idCanNang +
                '}';
    }
}