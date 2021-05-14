package com.example.iheath.entities.RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM HoSo")
    List<User> getAllUser();

    @Query("SELECT * FROM CanNang")
    List<CanNang> getAllCanNang();

    @Query("SELECT * FROM HoSo Where idUser= :idUser")
    List<User> getHSById(int idUser);

    @Query("SELECT * FROM CanNang Where idUser= :idUser")
    List<CanNang> getCanNangById(int idUser);

    @Query("DELETE FROM CanNang Where idUser= :idUser")
    void xoaCanNangId(int idUser);

    @Query("DELETE FROM CanNang Where ngay = :ngay")
    void xoaCanNangTheoNgay(int ngay);


    @Insert
    void themUser(User ... users);
    @Insert
    void themCanNangUser(CanNang ... canNangs);
    @Update
    void suaUser(User ... users);
    @Delete
    void xoaUser(User ... users);

    @Query("SELECT * FROM BMI")
    List<BMI> getAllBMI();

    @Query("SELECT * FROM BMI Where id= :id")
    List<BMI> getBMI( int id);

    @Insert
    void themBMI(BMI... bmis);

    @Query("DELETE from BMI")
    void xoaBMI();

    @Query("DELETE from calo")
    void xoaCalo();

    @Query("DELETE from calocannap")
    void xoacalocannap();

    @Query("SELECT * FROM calo")
    List<Calo> getAllCalo();

    @Query("SELECT * FROM calo Where id= :id")
    List<Calo> getCalo( int id);

    @Insert
    void themCalo(Calo... calos);

    @Query("SELECT * FROM calocannap")
    List<CaloCanNap> getAllCaloCanNap();

    @Query("SELECT * FROM calocannap Where id= :id")
    List<CaloCanNap> getCaloCanNap(int id);

    @Insert
    void themCaloCanGiam(CaloCanNap... caloCanGiam);

    @Query("SELECT * FROM  RSS")
    List<RSS> getAllRSS();

    @Query("SELECT * FROM RSS Where id= :id")
    List<RSS> getRSSbyId( int id);

    @Insert
    void themRSS(RSS... rsss);


    @Query("SELECT * FROM MonAnCuaBan")
    List<MonAn> getAllMonAN();
    @Query("SELECT * FROM ThanhPhanMonAn")
    List<ThanhPhanChiTiet> getAllThanhPhan();
    @Query("SELECT * FROM ThanhPhanMonAn where tenMonAn=:tenMonAn")
    List<ThanhPhanChiTiet> getAllThanhPhanMonAn(String tenMonAn);
    @Insert
    void themMonAn(MonAn...monAns);
    @Insert
    void themThanhPhan(ThanhPhanChiTiet...thanhPhanChiTiets);
    @Query("DELETE from MonAnCuaBan where tenMonAn=:tenMonAn")
    void xoaMonAn(String tenMonAn);

    @Query("DELETE from ThanhPhanMonAn where tenMonAn=:tenMonAn")
    void xoaThanhPhan(String tenMonAn);

}
