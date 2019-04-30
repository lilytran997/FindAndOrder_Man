package com.example.npmt.findandorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GetBill extends AppCompatActivity {

    static TextView ten,dc,tennh,sumAll;
    ListView foodList;
    Button datHang;
    private static ImageButton btnDir,btnStore,btnFood,btnBill;

    int sum = 0;

    ArrayList<food> arrayfood = new ArrayList<>();
    FoodAdapter fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_bill);

        Intent getInfo = this.getIntent();
        final Bill billInfo = (Bill) getInfo.getSerializableExtra("billInfo4");
        final String ID = getInfo.getStringExtra("id");

        dc = findViewById(R.id.direcInBill);
        dc.setText(billInfo.getAddress());
        dc.setTag(dc.getKeyListener());
        dc.setKeyListener(null);

        ten = findViewById(R.id.nameInBill);
        ten.setText(billInfo.getName());
        ten.setTag(ten.getKeyListener());
        ten.setKeyListener(null);

        tennh = findViewById(R.id.addressInBill);
        tennh.setText(billInfo.getTenNhaHang());
        tennh.setTag(tennh.getKeyListener());
        tennh.setKeyListener(null);

        foodList = findViewById(R.id.lvBill);
        for(int i=0;i < billInfo.arrayfood.size();i++){
            ThemMon(billInfo.getFoodItem(i));
        }

        final View myLV = findViewById(R.id.lvBill);
        myLV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                System.out.println("abcsdcmk"+Integer.toString(fa.getSumPrice()));
                sumAll.setText(Integer.toString(fa.getSumPrice()));
                return false;
            }
        });
        datHang = findViewById(R.id.btnDatHang);
        datHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toFinish = new Intent(GetBill.this,Finish.class);
                toFinish.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(toFinish);
            }
        });

        btnDir = findViewById(R.id.btn_dir);
        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackDir = new Intent(GetBill.this,InsertDirectory.class);
                comeBackDir.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(comeBackDir);
                finish();
            }
        });

        btnStore = findViewById(R.id.btn_store);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackStore = new Intent(GetBill.this,ChooseRestaurant.class);
                comeBackStore.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                comeBackStore.putExtra("infoBill1",billInfo);
                startActivity(comeBackStore);
                finish();
            }
        });
        btnFood = findViewById(R.id.btn_food_menu);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackFood = new Intent(GetBill.this,ChooseFood.class);
                comeBackFood.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                comeBackFood.putExtra("infoBill2",billInfo);
                comeBackFood.putExtra("id",ID);
                startActivity(comeBackFood);
                finish();
            }
        });
        btnBill = findViewById(R.id.btn_bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comeBackCustInf = new Intent(GetBill.this,CustomerIfomation.class);
                comeBackCustInf.putExtra("billInfo3",billInfo);
                comeBackCustInf.putExtra("id",ID);
                startActivity(comeBackCustInf);
                finish();
            }
        });
    }

    private void ThemMon(food info){
        arrayfood.add(info);
        fa = new FoodAdapter(this, arrayfood);
        foodList.setAdapter(fa);

        sumAll = findViewById(R.id.SumAll);
        sumAll.setText(Integer.toString(fa.getSumPrice()));
    }
}
