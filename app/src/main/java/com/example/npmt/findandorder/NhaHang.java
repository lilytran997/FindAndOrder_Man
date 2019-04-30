package com.example.npmt.findandorder;
import java.io.Serializable;

public class NhaHang implements Serializable {
    private String ten;
    private String diaChi;
    private int hinh;
    private String ID;


    public NhaHang(){
        ID = "";
        ten = "";
        hinh = 0;
        diaChi = "";
    }
    public NhaHang(String ID, String ten, String diaChi, int hinh) {
        this.ID = ID;
        this.ten = ten;
        this.diaChi = diaChi;
        this.hinh = hinh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    @Override
    public String toString(){
        return this.ten;
    }
}
