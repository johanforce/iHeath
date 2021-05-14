package com.example.iheath.entities;

import java.io.Serializable;

public class ThucPhamCanTinhCalo implements Serializable {

    public String tenSanPham;
    public Double soLuong;
    private Double calo;
    public Double nuoc;
    public Double dam;
    public Double beo;
    public Double bot;
    public Double xo;

    public ThucPhamCanTinhCalo() {

    }

    public ThucPhamCanTinhCalo(String tenSanPham, Double soLuong, Double calo, Double nuoc, Double dam, Double beo, Double bot, Double xo) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.calo = calo;
        this.nuoc = nuoc;
        this.dam = dam;
        this.beo = beo;
        this.bot = bot;
        this.xo = xo;
    }


    @Override
    public String toString() {
        return "ThucPhamCanTinhCalo{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", calo=" + calo +
                ", nuoc=" + nuoc +
                ", dam=" + dam +
                ", beo=" + beo +
                ", bot=" + bot +
                ", xo=" + xo +
                '}';
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Double getCalo() {
        return calo;
    }

    public void setCalo(Double calo) {
        this.calo = calo;
    }

    public Double getNuoc() {
        return nuoc;
    }

    public void setNuoc(Double nuoc) {
        this.nuoc = nuoc;
    }

    public Double getDam() {
        return dam;
    }

    public void setDam(Double dam) {
        this.dam = dam;
    }

    public Double getBeo() {
        return beo;
    }

    public void setBeo(Double beo) {
        this.beo = beo;
    }

    public Double getBot() {
        return bot;
    }

    public void setBot(Double bot) {
        this.bot = bot;
    }

    public Double getXo() {
        return xo;
    }

    public void setXo(Double xo) {
        this.xo = xo;
    }
}
