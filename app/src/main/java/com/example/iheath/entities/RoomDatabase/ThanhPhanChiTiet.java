package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "ThanhPhanMonAn" ,primaryKeys = {"id"})
public class ThanhPhanChiTiet implements Serializable {

    @NonNull
    @ColumnInfo(name="id")
    private int id;
    @NonNull
    @ColumnInfo(name="tenThanhPhan")
    private String tenThanhPhan;
    @NonNull
    @ColumnInfo(name="soLuong")
    private Double soLuong;
    @NonNull
    @ColumnInfo(name="calo")
    private Double calo;
    @NonNull
    @ColumnInfo(name="tenMonAn")
    private String tenMonAn;

    @Override
    public String toString() {
        return "ThanhPhanChiTiet{" +
                "id=" + id +
                ", tenThanhPhan='" + tenThanhPhan + '\'' +
                ", soLuong=" + soLuong +
                ", calo=" + calo +
                ", tenMonAn='" + tenMonAn + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTenThanhPhan() {
        return tenThanhPhan;
    }

    public void setTenThanhPhan(@NonNull String tenThanhPhan) {
        this.tenThanhPhan = tenThanhPhan;
    }

    @NonNull
    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(@NonNull Double soLuong) {
        this.soLuong = soLuong;
    }

    @NonNull
    public Double getCalo() {
        return calo;
    }

    public void setCalo(@NonNull Double calo) {
        this.calo = calo;
    }

    @NonNull
    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(@NonNull String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public ThanhPhanChiTiet(int id, @NonNull String tenThanhPhan, @NonNull Double soLuong, @NonNull Double calo, @NonNull String tenMonAn) {
        this.id = id;
        this.tenThanhPhan = tenThanhPhan;
        this.soLuong = soLuong;
        this.calo = calo;
        this.tenMonAn = tenMonAn;
    }
}
