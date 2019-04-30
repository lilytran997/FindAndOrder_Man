package com.example.npmt.findandorder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerIfomation extends AppCompatActivity {

    TextView dc,sdt,ten;
    Button ok;
    private static ImageButton btnDir,btnStore,btnFood,btnBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_ifomation);

        Intent getBillInfo = this.getIntent();
        final Bill bill = (Bill) getBillInfo.getSerializableExtra("billInfo3");
        final String ID = getBillInfo.getStringExtra("id");

        dc = findViewById(R.id.DCKH);
        dc.setText(bill.getAddress());

        ten = findViewById(R.id.TenKH);
        sdt = findViewById(R.id.SDT);
//sự kiện nhấn nút OK
        ok = findViewById(R.id.btnOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ten.getText().toString().isEmpty()){
                    Context ct1 = ten.getContext();
                    Toast.makeText(ct1,"Bạn chưa nhập tên kìa!",Toast.LENGTH_SHORT).show();
                }
                else{
                    bill.setName(ten.getText().toString());
                    if(dc.getText().toString().isEmpty()){
                        Context ct2 = dc.getContext();
                        Toast.makeText(ct2,"Bạn có chắc là đã nhập tên?",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        bill.setAddress(dc.getText().toString());
                        if(sdt.getText().toString().isEmpty()){
                            Context ct3 = sdt.getContext();
                            Toast.makeText(ct3,"Nhập sdt bạn ơi!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            bill.setPhoneNumber(sdt.getText().toString());
                            Intent toBill = new Intent(CustomerIfomation.this, GetBill.class);
                            toBill.putExtra("billInfo4", bill);
                            toBill.putExtra("id",ID);
                            startActivity(toBill);
                        }
                    }
                }
            }
        });

        btnDir = findViewById(R.id.btn_dir);
        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackDir = new Intent(CustomerIfomation.this,InsertDirectory.class);
                comeBackDir.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(comeBackDir);
                finish();
            }
        });

        btnStore = findViewById(R.id.btn_store);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackStore = new Intent(CustomerIfomation.this,ChooseRestaurant.class);
                comeBackStore.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                comeBackStore.putExtra("infoBill1",bill);
                startActivity(comeBackStore);
                finish();
            }
        });
        btnFood = findViewById(R.id.btn_food_menu);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent comeBackFood = new Intent(CustomerIfomation.this,ChooseFood.class);
                comeBackFood.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                comeBackFood.putExtra("infoBill2",bill);
                comeBackFood.putExtra("id",ID);
                startActivity(comeBackFood);
                finish();
            }
        });
        btnBill = findViewById(R.id.btn_bill);
        btnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ten.getText().toString().isEmpty()){
                    Context ct1 = ten.getContext();
                    Toast.makeText(ct1,"Bạn chưa nhập tên kìa!",Toast.LENGTH_SHORT).show();
                }
                else{
                    bill.setName(ten.getText().toString());
                    if(dc.getText().toString().isEmpty()){
                        Context ct2 = dc.getContext();
                        Toast.makeText(ct2,"Bạn có chắc là đã nhập địa chỉ?",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        bill.setAddress(dc.getText().toString());
                        if(sdt.getText().toString().isEmpty()){
                            Context ct3 = sdt.getContext();
                            Toast.makeText(ct3,"Nhập sdt bạn ơi!",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            bill.setPhoneNumber(sdt.getText().toString());
                            Intent toBill = new Intent(CustomerIfomation.this, GetBill.class);
                            toBill.putExtra("billInfo4", bill);
                            toBill.putExtra("id",ID);
                            startActivity(toBill);
                        }
                    }
                }
            }
        });
    }
}
