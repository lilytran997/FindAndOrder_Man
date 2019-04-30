package com.example.npmt.findandorder;
import java.io.Serializable;
import java.util.ArrayList;

public class Bill implements Serializable {

    String TenNhaHang,Name,PhoneNumber,Address,District;
    ArrayList<food> arrayfood;

    public Bill(String Name, String PhoneNumber, String Address,String District, String TenNhaHang, ArrayList<food> arrayfood) {
        this.Name = Name;//ten khach hang
        this.PhoneNumber = PhoneNumber;
        this.Address = Address;
        this.District = District;
        this.TenNhaHang = TenNhaHang;
        this.arrayfood = arrayfood;
    }

    public Bill(String Address,String District){
        Name = PhoneNumber = TenNhaHang ="Unknow";
        this.Address = Address;
        this.District = District;
        arrayfood = null;
    }


    public void setName(String Name){
        this.Name = Name;
    }
    public String getName(){
        return Name;
    }

    public void setDistrict(String District){
        this.District = District;
    }
    public String getDistrict(){
        return District;
    }

    public void setPhoneNumber(String PhoneNumber){
        this.PhoneNumber = PhoneNumber;
    }
    public String getPhoneNumber(){
        return PhoneNumber;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }
    public String getAddress(){
        return Address;
    }

    public void setTenNhaHang(String TenNhaHang){
        this.TenNhaHang = TenNhaHang;
    }
    public String getTenNhaHang(){
        return TenNhaHang;
    }

    public void setInfoFood(ArrayList<food> arrayfood){
        this.arrayfood = arrayfood;
    }
    public ArrayList<food> getInfoFood(){
        return arrayfood;
    }

    public food getFoodItem(int position){
        return arrayfood.get(position);
    }

    public String customerToString(){
        return "Name: "+Name+", phone num: "+PhoneNumber+", address: "+Address;
    }
}
