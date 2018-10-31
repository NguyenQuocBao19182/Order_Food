package com.example.philong.order.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.order.Adapter.Food_Adapter_Order;
import com.example.philong.order.Object.Food;
import com.example.philong.order.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FormCustomer extends AppCompatActivity {
    String urlGetData="http://192.168.56.1:81/androidQlquanan/getdata.php";
     ArrayList<Food> foodOrderArrayList = new ArrayList<>();
    Food_Adapter_Order foodOrderAdapter;
    RecyclerView recyclerView;
    Button btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_customer);

        GetData(urlGetData);
        initView();
    }

    public void GetData (String url){

         RequestQueue requestQueue= Volley.newRequestQueue(this);
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    //khi doc duoc json
                    public void onResponse(JSONArray response) {
                        foodOrderArrayList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                foodOrderArrayList.add(new Food(
                                        object.getInt("ID"),
                                        object.getString("Ten"),
                                        object.getString("Gia")//trùng với định nghĩa contructor của php $this->ID
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        foodOrderAdapter.notifyDataSetChanged();
                    }
                },
                //khi doc json bi loi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormCustomer.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    public void initView(){
        btnXacNhan=findViewById(R.id.button_xacnhan);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(FormCustomer.this,xacnhan.class);
                startActivity(intent);
                //intent.putExtra("a",recyclerView.)



            }
        });
        recyclerView=findViewById(R.id.recyclerview_1);

        foodOrderAdapter = new Food_Adapter_Order(foodOrderArrayList, getApplicationContext());

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(foodOrderAdapter);

       /* final ArrayList<Food> foodArrayList = new ArrayList<>();

        foodArrayList.add(new Food(1,"sd","sd"));
        foodArrayList.add(new Food(2,"sd","sd"));
        foodArrayList.add(new Food(3,"sd","sd"));
        foodArrayList.add(new Food(4,"sd","sd"));
        Food_Adapter_Order FoodAdapter = new Food_Adapter_Order(foodArrayList, getApplicationContext());*/



    }
}
