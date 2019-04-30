package com.example.npmt.findandorder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class InsertDirectory extends AppCompatActivity {

    private static EditText edtNhapDiaChi;
    private static Button btnNhanDiaChi,btnChonQuan,btnAddRest;
    private static ImageButton btnDir,btnStore,btnFoodMenu,btnBill;

    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_directory);

        edtNhapDiaChi = findViewById(R.id.editTextNhapDiaChi);
        edtNhapDiaChi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b==false){
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        //
        btnChonQuan = findViewById(R.id.btnChonQuan);
        btnChonQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
                edtNhapDiaChi.clearFocus();
            }
        });
        //
        btnNhanDiaChi = findViewById(R.id.btnNhanDiaChi);
        btnNhanDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Diachi = edtNhapDiaChi.getText().toString();

                edtNhapDiaChi.clearFocus();

                if (ID == null) {
                    Context ct = (Context) btnNhanDiaChi.getContext();
                    Toast.makeText(ct,"Bạn chưa chọn quận",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Diachi.isEmpty()||Diachi.length()==0||Diachi.equals("")||Diachi==null){
                        Context ct = (Context) btnNhanDiaChi.getContext();
                        Toast.makeText(ct,"Bạn chưa nhập địa chỉ kìa",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Bill info = new Bill(Diachi,ID);
                        Intent chooseRestIntent = new Intent(InsertDirectory.this, ChooseRestaurant.class);
                        chooseRestIntent.putExtra("infoBill1", info);
                        startActivity(chooseRestIntent);
                    }
                }
            }
        });
        //
        btnAddRest = findViewById(R.id.btnAddRest);
        btnAddRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAddRest = new Intent(InsertDirectory.this,AddRest.class);
                startActivity(goToAddRest);
            }
        });

        btnDir = findViewById(R.id.btn_dir);
        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = btnDir.getContext();
                Toast.makeText(ct,"Bạn đang ở đó nè",Toast.LENGTH_SHORT).show();
            }
        });
        //
        btnStore = findViewById(R.id.btn_store);
        btnStore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String Diachi = edtNhapDiaChi.getText().toString();

                if (ID == null) {
                    Context ct = (Context) btnStore.getContext();
                    Toast.makeText(ct,"Bạn chưa chọn quận",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Diachi.isEmpty()||Diachi.length()==0||Diachi.equals("")||Diachi==null){
                        Context ct = (Context) btnStore.getContext();
                        Toast.makeText(ct,"Bạn chưa nhập địa chỉ kìa",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Bill info = new Bill(Diachi,ID);
                        Intent chooseRestIntent = new Intent(InsertDirectory.this, ChooseRestaurant.class);
                        chooseRestIntent.putExtra("infoBill1", info);
                        startActivity(chooseRestIntent);
                    }
                }
            }

        });

        btnFoodMenu = findViewById(R.id.btn_food_menu);
        btnFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ct = (Context) btnFoodMenu.getContext();
                Toast.makeText(ct,"Chưa đủ thông tin để đến menu.",Toast.LENGTH_SHORT).show();
            }
        });

        btnBill = findViewById(R.id.btn_bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context ct = (Context) btnBill.getContext();
                Toast.makeText(ct,"Chưa đủ thông tin để đến bill.",Toast.LENGTH_SHORT).show();
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
