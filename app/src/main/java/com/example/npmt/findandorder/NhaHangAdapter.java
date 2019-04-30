package com.example.npmt.findandorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class NhaHangAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<NhaHang> nhaHangList;

    //constructor

    public NhaHangAdapter(Context context, int layout, List<NhaHang> nhaHangList) {
        this.context = context;
        this.layout = layout;
        this.nhaHangList = nhaHangList;
    }

    @Override
    public int getCount() {
        return nhaHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        //anh xa view
        TextView txtTenNH = (TextView) convertView.findViewById(R.id.txtViewTenNH);
        TextView txtDiaChiNH = (TextView) convertView.findViewById(R.id.txtViewDiaChiNH);
        ImageView imgHinhNH = (ImageView) convertView.findViewById(R.id.imgViewHinhNH);
        //gan gia tri
        NhaHang nhaHang = nhaHangList.get(position);
        txtTenNH.setText(nhaHang.getTen());
        txtDiaChiNH.setText(nhaHang.getDiaChi());
        imgHinhNH.setImageResource(nhaHang.getHinh());

        return convertView;
    }
}