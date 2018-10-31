package com.example.philong.order.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.order.Adapter.Food_Adapter_Confirm;
import com.example.philong.order.Object.Food;
import com.example.philong.order.Object.FoodOrder;
import com.example.philong.order.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class xacnhan extends AppCompatActivity {
    FormCustomer formCustomer;
    ArrayList<FoodOrder> foodOrderArrayList;
    Food_Adapter_Confirm food_adapter_confirm;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhan);

        initView();
    }
    public void initView(){

        /*final ArrayList<Food_XacNhan> food_xacnhan=new ArrayList();

        food_xacnhan.add(new Food_XacNhan(1,"asd","33232",2));
        food_xacnhan.add(new Food_XacNhan(2,"asd1","33232",2));
        food_xacnhan.add(new Food_XacNhan(3,"asd2","33232",2));
        */
        RecyclerView recyclerView = findViewById(R.id.recyclerview_2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);
        food_adapter_confirm= new Food_Adapter_Confirm(foodOrderArrayList, getApplicationContext());
        recyclerView.setAdapter(food_adapter_confirm);
    }



}
