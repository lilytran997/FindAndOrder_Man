package com.example.npmt.findandorder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseRestaurant extends AppCompatActivity {

    private static ImageButton btnDir,btnStore,btnFood,btnBill;
    private static TextView tv;
    private ListView lvNhaHang;
    NhaHangAdapter adapter;
    ArrayList<NhaHang> arrayNhaHang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);

        Intent chooserestintent = this.getIntent();

        final Bill infoBill =(Bill) chooserestintent.getSerializableExtra("infoBill1");

        tv =  findViewById(R.id.direcInBill);
        tv.setText("Gần "+ infoBill.getAddress() + " có các cửa hàng: ");

        //test chay dung.
        lvNhaHang = findViewById(R.id.list_restaurant);

//       so sánh database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference RestInDistric = database.getReference(infoBill.getDistrict());

        RestInDistric.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String tennh,IDnh,Diachi;
                for(DataSnapshot data:dataSnapshot.getChildren()) {
                    tennh = data.child("Name").getValue().toString();
                    IDnh = data.getKey();
                    Diachi = data.child("Address").getValue().toString();

                    arrayNhaHang.add(new NhaHang(IDnh,tennh,Diachi,R.drawable.shop_1_white));

                    infoBill.setTenNhaHang(tennh);
                }
                adapter = new NhaHangAdapter(ChooseRestaurant.this, R.layout.list_restaurant, arrayNhaHang);
                lvNhaHang.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Có lỗi!"+databaseError.toString());
            }
        });

//sự kiện nhấn item trong list view
        lvNhaHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//check
                infoBill.setTenNhaHang(arrayNhaHang.get(position).getTen());

                Intent chooseRest = new Intent(ChooseRestaurant.this, ChooseFood.class);
                chooseRest.putExtra("infoBill2",infoBill);
                chooseRest.putExtra("id",arrayNhaHang.get(position).getID());
                System.out.println("id"+arrayNhaHang.get(position).getID());
                startActivity(chooseRest);
            }
        });
//sự kiện nhấn nút tìm nhà hàng
        btnDir = findViewById(R.id.btn_dir);
        btnDir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent returnDir = new Intent(ChooseRestaurant.this,InsertDirectory.class);
                startActivity(returnDir);
                finish();
            }
        });
//sự kiện nhấn nút nhà hàng
        btnStore = findViewById(R.id.btn_store);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ct = btnStore.getContext();
                Toast.makeText(ct,"Bạn đang ở bước này rồi!",Toast.LENGTH_SHORT).show();
            }
        });
//sự khiện nhấn 2 nút food và bill
        btnFood = findViewById(R.id.btn_food_menu);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ct = btnFood.getContext();
                Toast.makeText(ct,"Bạn phải chọn (nhấn) vào một nhà hàng!",Toast.LENGTH_SHORT).show();
            }
        });

        btnBill = findViewById(R.id.btn_bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ct = btnBill.getContext();
                Toast.makeText(ct,"Chưa đủ thông tin để đến bill đâu!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
