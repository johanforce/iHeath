package com.example.iheath.entities.RoomDatabase;


import androidx.room.RoomDatabase;

import com.example.iheath.App;

public class UserManager {
    private static UserManager instance;
    private UserManager(){

    }

    public  static UserManager getInstance(){
        if(instance==null){
            instance= new UserManager();
        }
        return instance;
    }

    public void themUser(final OnHSCallBack callBack, final User user){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themUser(user);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void xoaCanNang(final OnHSCallBack callBack, final int id){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaCanNangId(id);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void xoaCanNangTheoNgay(final OnHSCallBack callBack, final int ngay){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaCanNangTheoNgay(ngay);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void themCanNangUser(final OnHSCallBack callBack, final CanNang canNang){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themCanNangUser(canNang);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void suaUser(final OnHSCallBack callBack, final User user){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().suaUser(user);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void xoaUser(final OnHSCallBack callBack, final User user){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().suaUser(user);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public interface  OnHSCallBack{
        void callBack(Object data);
    }

    public void getAllUser(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllUser());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void getAllCanNang(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllCanNang());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getCanNangUser(final OnHSCallBack cb, int id){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getCanNangById(id));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void themBMI(final OnHSCallBack callBack, final BMI bmi){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themBMI(bmi);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void themCalo(final OnHSCallBack callBack, final Calo calo){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themCalo(calo);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void themCaloCanNap(final OnHSCallBack callBack, final CaloCanNap caloCanNap){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themCaloCanGiam(caloCanNap);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void getAllBMI(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllBMI());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getAllCalo(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllCalo());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getAllCaloCanNap(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllCaloCanNap());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }



    public void getBMIbyId(final OnHSCallBack cb, int id){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getBMI(id));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void xoaBMI(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaBMI();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void xoaCalo(final OnHSCallBack callBack){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaCalo();
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void xoaCaloCanNap(final OnHSCallBack callBack){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoacalocannap();
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }

    public void getAllRSS(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllRSS());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }



    public void getRSSbyId(final OnHSCallBack cb, int id) {
        new Thread() {
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getRSSbyId(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void themRSS(final OnHSCallBack callBack, RSS rsss){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themRSS(rsss);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void themMonAn(final OnHSCallBack callBack, final MonAn monAn){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themMonAn(monAn);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void themThanhPhan(final OnHSCallBack callBack, final ThanhPhanChiTiet thanhPhanChiTiet){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().themThanhPhan(thanhPhanChiTiet);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void getAllMonAn(final OnHSCallBack cb){
        new Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllMonAN());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void getAllThanhPhanMonAn(final OnHSCallBack cb, String tenMonAn){
        new  Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllThanhPhanMonAn(tenMonAn));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void getAllThanhPhan(final OnHSCallBack cb){
        new  Thread(){
            @Override
            public void run() {
                try {
                    cb.callBack(App.getInstance().getUserDB().getUserDAO().getAllThanhPhan());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void xoaMonAn(final OnHSCallBack callBack, final String tenMonAn){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaMonAn(tenMonAn);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
    public void xoaThanhPhan(final OnHSCallBack callBack, final String tenMonAn){
        new  Thread(){
            @Override
            public void run() {
                try {
                    App.getInstance().getUserDB().getUserDAO().xoaThanhPhan(tenMonAn);
                    callBack.callBack(true);
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.callBack(false);
                }
            }
        }.start();
    }
}
