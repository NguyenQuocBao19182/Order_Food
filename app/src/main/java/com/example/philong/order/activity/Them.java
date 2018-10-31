package com.example.philong.order.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.order.R;

import java.util.HashMap;
import java.util.Map;

public class Them extends AppCompatActivity {
    String urlInsert="http://192.168.56.1:81/androidQlquanan/insert.php";
    EditText edtTen;
    EditText edtGia;
    Button btnThem;
    Button btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        init();

    }
    public void init(){
        edtTen=findViewById(R.id.edt_ghi);
        edtGia=findViewById(R.id.edt_gia);
        btnThem=findViewById(R.id.btn_them);
        btnHuy=findViewById(R.id.btn_huy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tenMon=edtTen.getText().toString().trim();
                final String gia=edtGia.getText().toString().trim();
                if (tenMon.isEmpty() || gia.isEmpty()) {
                    Toast.makeText(Them.this, "Nhap du thong tin nha", Toast.LENGTH_SHORT).show();
                }
                else themMon(urlInsert);
                }
        });
    }


    private void themMon(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //POST để đấy lên
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//khi insert thành công
                if(response.trim().equals("success")){//success là báo thành công trên php lấy xuống để dùng
                    Toast.makeText(Them.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Them.this,MainActivity.class));
                }
                else {

                    Toast.makeText(Them.this, "có lỗi gì rồi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {//khi insert thất bại
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Them.this, "Loi ", Toast.LENGTH_SHORT).show();
                Log.d("AAA","Loi!\n"+error.toString());//chi tiết lỗi
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //tạo map để đẩy lên
                Map<String,String> params=new HashMap<>();

                params.put("tenmon",edtTen.getText().toString().trim());//đẩy lên Json hotenSV với edtTen .trim để dùng khoảng trắng
                params.put("giamon",edtGia.getText().toString().trim());//đẩy lên Json hotenSV với edtTen
                return params;
            }
        };

        requestQueue.add(stringRequest);//add vao

    }

}
