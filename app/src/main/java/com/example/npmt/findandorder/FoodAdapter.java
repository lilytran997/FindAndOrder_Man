package com.example.npmt.findandorder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private static List<food> foodList;
    static int[]arraySum;
    static int sum = 0;
    //constructor

    public FoodAdapter(Context context, List<food> foodList) {
        this.context = context;
        this.foodList = foodList;
        arraySum = new int[foodList.size()];
    }

    public int getSumPrice(){
        return sum;
    }

    public List<food> getFoodList() {
        return foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_food, null);
            //anh xa view
            final ViewHolder holder = new ViewHolder();

            holder.TenMon = (TextView) convertView.findViewById(R.id.txtViewTenMon);
            holder.SL = (TextView) convertView.findViewById(R.id.SoLuongFood);
            holder.gia = (TextView) convertView.findViewById(R.id.textViewGia);
            holder.imgMon = (ImageView) convertView.findViewById(R.id.imgViewFood);
            holder.plusbtn = (ImageButton) convertView.findViewById(R.id.imageAdd);
            holder.subbtn = (ImageButton) convertView.findViewById(R.id.imageSub);

            convertView.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) convertView.getTag();

        final food foodlink = foodList.get(position);

        holder.TenMon.setText(foodlink.getTen());
        holder.SL.setText(Integer.toString(foodlink.getsl()));
        holder.imgMon.setImageResource(foodlink.getHinh());
        holder.CapNhatGia(foodlink.getsl()*Integer.parseInt(foodlink.getGia()),position);

        arraySum[position] = foodlink.getsl()*Integer.parseInt(foodlink.getGia());

        //sự kiện nhấn nút +
        holder.plusbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(holder.SL.getText().toString())+1;
                if(slmoi>=10) {
                    Context ct = holder.plusbtn.getContext();
                    Toast.makeText(ct,"Nhiều quá rồi bạn ơi!", Toast.LENGTH_SHORT).show();
                    slmoi = 9;
                }
                holder.SL.setText(Integer.toString(slmoi));
                foodlink.setsl(slmoi);
                holder.CapNhatGia(Integer.parseInt(foodlink.getGia())*slmoi,position);
                notifyDataSetInvalidated();
            }
        });
        //sự kiện nhấn nút trừ
        holder.subbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(holder.SL.getText().toString())-1;
                if(slmoi<0) {
                    Context ct = holder.subbtn.getContext();
                    Toast.makeText(ct,"Hiện tại app mình chưa hỗ trợ số món âm nhá!", Toast.LENGTH_SHORT).show();
                    slmoi = 0;
                }
                holder.SL.setText(Integer.toString(slmoi));
                foodlink.setsl(slmoi);
                holder.CapNhatGia(Integer.parseInt(foodlink.getGia())*slmoi,position);
                notifyDataSetInvalidated();
            }
        });

        holder.imgMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = holder.imgMon.getContext();
                Toast.makeText(ct,"Món "+holder.TenMon.getText()+" này rất ngon!", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    final static class ViewHolder{
        TextView TenMon,SL,gia;
        ImageView imgMon;
        ImageButton plusbtn,subbtn;


        public void CapNhatGia(int GiaMoi,int position){
            sum = 0;
            gia.setText(Integer.toString(GiaMoi));
            arraySum[position] = GiaMoi;

            for(int i=0; i<foodList.size();i++){
                sum += arraySum[i];
            }
        }
    }
}
