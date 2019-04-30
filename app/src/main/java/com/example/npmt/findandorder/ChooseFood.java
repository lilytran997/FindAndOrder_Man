package com.example.npmt.findandorder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseFood extends AppCompatActivity {

    TextView tv;
    ListView foodList;
    ArrayList<food> arrayfood = new ArrayList<>();
    FoodAdapter fa;
    Button dathang;
    private static ImageButton btnDir,btnStore,btnFood,btnBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_food);

        Intent getInfo = this.getIntent();
        final Bill billInfo =(Bill) getInfo.getSerializableExtra("infoBill2");
        final String District = billInfo.getDistrict();
        final String ID = getInfo.getStringExtra("id");//id nhà hàng đã được chọn ở ChooseRestaurant

        tv = findViewById(R.id.direcInBill);
        tv.setText("Đây là menu của "+billInfo.getTenNhaHang()+" :");

        foodList = findViewById(R.id.list_food);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference MenuInRest = database.getReference(District).child(ID).child("Menu");

        MenuInRest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tenmon,idmon,gia;

                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    tenmon = data.child("Name").getValue().toString();
                    idmon = data.getKey();
                    gia = data.child("Price").getValue().toString();

                    System.out.println("Children data "+data.child("Name").getValue().toString());
                    arrayfood.add( new food(idmon,tenmon,gia,R.drawable.dinner,0));
                }
                fa = new FoodAdapter(ChooseFood.this, arrayfood);
                foodList.setAdapter(fa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Có lỗi!"+databaseError.toString());
            }
        });

//sự kiện nhấn nút đặt hàng
        dathang = findViewById(R.id.dathang);
        dathang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//Cài những món có sl khác 0 vào
                ArrayList<food> foodToBill = new ArrayList<>();
                for(int i=0;i<arrayfood.size();i++) {
                    if(arrayfood.get(i).getsl()!=0)
                        foodToBill.add(arrayfood.get(i));
                }
                if(foodToBill.size()==0){
                    Context ct = dathang.getContext();
                    Toast.makeText(ct,"Ban chua chon bat ki mon nao!",Toast.LENGTH_SHORT).show();
                }
                else{
                    billInfo.setInfoFood(foodToBill);
                    Intent putFoodInfo = new Intent(ChooseFood.this,CustomerIfomation.class);
                    putFoodInfo.putExtra("billInfo3",billInfo);
                    putFoodInfo.putExtra("id",ID);
                    startActivity(putFoodInfo);
                }
            }
        });

        btnDir = findViewById(R.id.btn_dir);
        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackDir = new Intent(ChooseFood.this,InsertDirectory.class);
                comeBackDir.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(comeBackDir);
                finish();
            }
        });

        btnStore = findViewById(R.id.btn_store);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackStore = new Intent(ChooseFood.this,ChooseRestaurant.class);
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
                Context ct = btnFood.getContext();
                Toast.makeText(ct,"Bạn đang ở bước này!",Toast.LENGTH_SHORT).show();
            }
        });

        btnBill = findViewById(R.id.btn_bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<food> foodToBill = new ArrayList<>();
                for(int i=0;i<arrayfood.size();i++) {
                    if(arrayfood.get(i).getsl()!=0)
                        foodToBill.add(arrayfood.get(i));
                }
                if(foodToBill.size()==0){
                    Context ct = btnBill.getContext();
                    Toast.makeText(ct,"Ban chua chon bat ki mon nao!",Toast.LENGTH_SHORT).show();
                }
                else{
                    billInfo.setInfoFood(foodToBill);
                    Intent putFoodInfo = new Intent(ChooseFood.this,CustomerIfomation.class);
                    putFoodInfo.putExtra("billInfo3",billInfo);
                    putFoodInfo.putExtra("id",ID);
                    startActivity(putFoodInfo);
                }
            }
        });
    }
}
