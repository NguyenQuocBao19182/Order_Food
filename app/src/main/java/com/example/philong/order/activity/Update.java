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
import com.example.philong.order.Object.Food;
import com.example.philong.order.R;

import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity {
    String urlUpdate="http://192.168.56.1:81/androidQlquanan/update.php";
    private EditText edtTenUpdate,edtGiaUpdate;
    private Button btnUpdate;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        anhXa();

        Intent intent=getIntent();
        Food food=(Food) intent.getSerializableExtra("dataFood");
        id=food.getID();  //gởi kèm theo id để viết query
        edtTenUpdate.setText(food.getFoodName());
        edtGiaUpdate.setText(food.getPrice());
    }

    void anhXa(){
        btnUpdate=findViewById(R.id.btn_update1);

        edtTenUpdate=findViewById(R.id.edt_ghi1);
        edtGiaUpdate=findViewById(R.id.edt_gia1);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmon=edtTenUpdate.getText().toString().trim();
                String giamon=edtGiaUpdate.getText().toString().trim();
                if(tenmon.isEmpty()||giamon.isEmpty()){
                    Toast.makeText(Update.this, "Đừng bỏ trống", Toast.LENGTH_SHORT).show();
                }
                else CapNhap(urlUpdate);
            }
        });
    }
    public void CapNhap(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){//success là báo thành công trên php lấy xuống để dùng
                    Toast.makeText(Update.this, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Update.this,MainActivity.class));
                }
                else {

                    Toast.makeText(Update.this, "có lỗi gì rồi", Toast.LENGTH_SHORT).show();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Update.this, "Loi ", Toast.LENGTH_SHORT).show();
                Log.d("AAA","Loi!\n"+error.toString());//chi tiết lỗi
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //tạo map để đẩy lên
                Map<String,String> params=new HashMap<>();

                params.put("idmon", String.valueOf(id));
                params.put("tenmon",edtTenUpdate.getText().toString().trim());//đẩy lên Json .trim để dùng khoảng trắng
                params.put("giamon",edtGiaUpdate.getText().toString().trim());//đẩy lên Json
                return params;
            }

        };
        requestQueue.add(stringRequest);
    }
}
