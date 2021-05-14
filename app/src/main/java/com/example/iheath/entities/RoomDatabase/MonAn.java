package com.example.iheath.entities.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

@Entity(tableName = "MonAnCuaBan" ,primaryKeys = {"id"})
public class MonAn implements Serializable {
    @NonNull
    @ColumnInfo(name="id")
    private int id;
    @NonNull
    @ColumnInfo(name="tenMonAn")
    private String tenMonAn;
    @NonNull
    @ColumnInfo(name="luongCalo")
    private Double calo;

    @Override
    public String toString() {
        return "MonAn{" +
                "id=" + id +
                ", tenMonAn='" + tenMonAn + '\'' +
                ", calo=" + calo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public MonAn(int id, @NonNull String tenMonAn, @NonNull Double calo) {
        this.id = id;
        this.tenMonAn = tenMonAn;
        this.calo = calo;
    }
}
