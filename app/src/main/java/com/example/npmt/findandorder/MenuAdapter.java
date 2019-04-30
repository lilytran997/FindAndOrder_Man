package com.example.npmt.findandorder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context context;
    private List<food> listFood;

    public MenuAdapter(Context context,List<food> listFood){
        this.context = context;
        this.listFood = listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int position) {
        return listFood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            final LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_add_menu, null);
            //anh xa view
            final ViewHolder holder = new ViewHolder();

            holder.TenMon = (TextView) convertView.findViewById(R.id.inNameFood);
            holder.gia = (TextView) convertView.findViewById(R.id.inPrice);
            holder.check = (ImageButton) convertView.findViewById(R.id.btnInComplete);
            holder.plus = (ImageButton) convertView.findViewById(R.id.btnInPlus);


            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.TenMon.setHint(listFood.get(position).getTen());
        holder.gia.setHint(listFood.get(position).getGia());

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = holder.TenMon.getText().toString();
                String gia = holder.gia.getText().toString();

                if(ten.isEmpty()||ten.equals("")||ten.length()==0||ten==null||gia.isEmpty()||gia.equals("")||gia.length()==0||gia==null){
                    Toast.makeText(holder.check.getContext(),"Bạn chưa nhập đủ tên và giá!",Toast.LENGTH_SHORT).show();
                }else {
                    listFood.get(position).setTen(ten);
                    listFood.get(position).setGia(gia);
                    Toast.makeText(holder.check.getContext(),"Món đã được thêm vào menu",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFood.add(new food());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    final static class ViewHolder{
        TextView TenMon,gia;
        ImageButton check,plus;
    }
}
