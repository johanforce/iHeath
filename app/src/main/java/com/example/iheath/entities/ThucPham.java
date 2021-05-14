package com.example.iheath.entities;

import java.io.Serializable;

public class ThucPham implements Serializable {
    public String id;
    public String nhomthucpham;
    public String name;
    public Double nangluong;
    public Double nuoc;
    public Double dam;
    public Double beo;
    public Double bot;
    public Double xo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNhomthucpham() {
        return nhomthucpham;
    }

    public void setNhomthucpham(String nhomthucpham) {
        this.nhomthucpham = nhomthucpham;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getNangluong() {
        return nangluong;
    }

    public void setNangluong(Double nangluong) {
        this.nangluong = nangluong;
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

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", nhomthucpham='" + nhomthucpham + '\'' +
                ", name='" + name + '\'' +
                ", nangluong='" + nangluong + '\'' +
                ", nuoc='" + nuoc + '\'' +
                ", dam='" + dam + '\'' +
                ", beo='" + beo + '\'' +
                ", bot='" + bot + '\'' +
                ", xo='" + xo + '\'' +
                '}';
    }

}
