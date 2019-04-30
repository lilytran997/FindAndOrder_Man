package com.example.npmt.findandorder;

import java.io.Serializable;

public class food implements Serializable {
    private String ten;
    private int hinh;
    private String ID;
    private String Gia;
    private int sl;

    public food(){
        ten ="Món 1";
        Gia ="10000";
    }
    public food(String ID, String ten, String Gia, int hinh, int sl) {
        this.ID = ID;
        this.ten = ten;
        this.Gia = Gia;
        this.hinh = hinh;
        this.sl = sl;
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

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String Gia) {
        this.Gia = Gia;
    }

    public int getsl() {
        return sl;
    }

    public void setsl(int sl) {
        this.sl = sl;
    }

    @Override
    public String toString(){
        return this.ten;
    }

}