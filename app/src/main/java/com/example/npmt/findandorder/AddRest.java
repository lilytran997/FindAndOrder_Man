package com.example.npmt.findandorder;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddRest extends AppCompatActivity {

    private static TextView tenNH,diaChi,lbName,lbDir;
    private static Button btnChonQuan,btnThemNhaHang;
    private static ImageButton check,plus;
    private String ID,namRest,dirRest;
    private ListView lvMenu;
    private ArrayList<food> listFood = new ArrayList<>();
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rest);

        btnChonQuan = findViewById(R.id.btnChonQuan);
        btnChonQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });

        listFood.add(new food());
        lvMenu = findViewById(R.id.lvAddRest);
        menuAdapter = new MenuAdapter(AddRest.this,listFood);
        lvMenu.setAdapter(menuAdapter);
        lvMenu.invalidateViews();

        tenNH = findViewById(R.id.inName);
        diaChi = findViewById(R.id.inDir);
        lbName = findViewById(R.id.LableName);
        lbDir = findViewById(R.id.lableDir);


        lvMenu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    tenNH.setVisibility(View.INVISIBLE);
                    diaChi.setVisibility(View.INVISIBLE);
                    lbName.setVisibility(View.INVISIBLE);
                    lbDir.setVisibility(View.INVISIBLE);
                    btnChonQuan.setVisibility(View.INVISIBLE);
                }
                else{
                    tenNH.setVisibility(View.VISIBLE);
                    diaChi.setVisibility(View.VISIBLE);
                    lbName.setVisibility(View.VISIBLE);
                    lbDir.setVisibility(View.VISIBLE);
                    btnChonQuan.setVisibility(View.VISIBLE);
                }
            }
        });

        btnThemNhaHang = findViewById(R.id.btnAddRest);
        btnThemNhaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                namRest = tenNH.getText().toString();
                dirRest = diaChi.getText().toString();
                System.out.println("id "+ID+"dir "+dirRest+"name "+namRest);
                if(ID==null||namRest==null||dirRest==null||ID.isEmpty()||namRest.isEmpty()||dirRest.isEmpty()){
                    Toast.makeText(AddRest.this,"Bạn chưa nhập đủ thông tin!!!",Toast.LENGTH_SHORT).show();
                }else {
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference RestInDistric = database.getReference(ID);
                    RestInDistric.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String newID = Long.toString(dataSnapshot.getChildrenCount()+1);
                            RestInDistric.child(newID).child("Name").setValue(namRest);
                            RestInDistric.child(newID).child("Address").setValue(dirRest);

                            for(int i = 0; i < listFood.size();i++){
                                RestInDistric.child(newID).child("Menu").child(Integer.toString(i+1)).child("Name").setValue(listFood.get(i).getTen().toString());
                                RestInDistric.child(newID).child("Menu").child(Integer.toString(i+1)).child("Price").setValue(listFood.get(i).getGia().toString());
                            }

                            RestInDistric.removeEventListener(this);

                            Toast.makeText(AddRest.this,"Đã thêm nhà hàng thành công!",Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(AddRest.this, InsertDirectory.class);
                                    startActivity(intent);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    finish();
                                }
                            },2000);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            System.out.println("Có lỗi!"+databaseError.toString());
                        }
                    });
                }
            }
        });


    }

    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btnChonQuan);
        popupMenu.getMenuInflater().inflate(R.menu.menu_quan,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuQ1: btnChonQuan.setText("Quận 1");ID="1";
                        break;
                    case R.id.menuQ2: btnChonQuan.setText("Quận 2");ID="2";
                        break;
                    case R.id.menuQ3: btnChonQuan.setText("Quận 3");ID = "3";
                        break;
                    case R.id.menuQ4: btnChonQuan.setText("Quận 4");ID = "4";
                        break;
                    case R.id.menuQ5: btnChonQuan.setText("Quận 5");ID = "5";
                        break;
                    case R.id.menuQ6: btnChonQuan.setText("Quận 6");ID = "6";
                        break;
                    case R.id.menuQ7: btnChonQuan.setText("Quận 7");ID = "7";
                        break;
                    case R.id.menuQ8: btnChonQuan.setText("Quận 8");ID = "8";
                        break;
                    case R.id.menuQ9: btnChonQuan.setText("Quận 9");ID = "9";
                        break;
                    case R.id.menuQ10: btnChonQuan.setText("Quận 10");ID = "10";
                        break;
                    case R.id.menuQ11: btnChonQuan.setText("Quận 11");ID = "11";
                        break;
                    case R.id.menuQ12: btnChonQuan.setText("Quận 12");ID = "12";
                        break;
                    case R.id.menuQBT: btnChonQuan.setText("Quận Bình Thạnh");ID = "BT";
                        break;
                    case R.id.menuQBt: btnChonQuan.setText("Quận Bình Tân");ID ="Bt";
                        break;
                    case R.id.menuQGV: btnChonQuan.setText("Quận Gò Vấp");ID = "GV";
                        break;
                    case R.id.menuQPN: btnChonQuan.setText("Quận Phú Nhuận");ID = "PN";
                        break;
                    case R.id.menuQTD: btnChonQuan.setText("Quận Thủ Đức");ID = "TD";
                        break;
                    case R.id.menuQTP: btnChonQuan.setText("Quận Tân Phú");ID = "TP";
                        break;
                    case R.id.menuTB: btnChonQuan.setText("Quận Tân Bình");ID = "TB";
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
}
