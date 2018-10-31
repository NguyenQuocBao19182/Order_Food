package com.example.philong.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.philong.order.Adapter.FoodAdapter;
import com.example.philong.order.Object.Food;
import com.example.philong.order.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
        String urlGetData="http://192.168.56.1:81/androidQlquanan/getdata.php";
        String urlDeleteData="http://192.168.56.1:81/androidQlquanan/delete.php";
    ArrayList<Food> foodArrayList ;

    private ListView listView;
    FoodAdapter foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.list_view_1);

        foodArrayList=new ArrayList<>();
        foodAdapter=new FoodAdapter(this,R.layout.list_item,foodArrayList);
        listView.setAdapter(foodAdapter);

        GetData(urlGetData);







    }



    public void GetData (String url){

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //GET để lấy xuống
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    //khi doc duoc json
                    public void onResponse(JSONArray response) {
                        foodArrayList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                               foodArrayList.add(new Food(
                                       object.getInt("ID"),
                                        object.getString("Ten"),
                                        object.getString("Gia")//trùng với định nghĩa contructor của php $this->ID
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        foodAdapter.notifyDataSetChanged();
                    }
                },
                //khi doc json bi loi
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

    }

    //Xóa
    public void DeleteFood(final int idfood){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, urlDeleteData, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//thực thi thành công quay về getdata
                if(response.trim().equals("success")){
                    Toast.makeText(MainActivity.this, "XoaThanhCong", Toast.LENGTH_SHORT).show();
                    GetData(urlGetData);
                }
                else Toast.makeText(MainActivity.this, "loi xoa", Toast.LENGTH_SHORT).show();
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Loi xoa", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("idFood", String.valueOf(idfood));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_monan,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_them)
        {
            startActivity(new Intent(MainActivity.this,Them.class));
        }
        return super.onOptionsItemSelected(item);
    }





}
