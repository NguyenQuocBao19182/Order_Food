package com.example.philong.order.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philong.order.Object.Food;
import com.example.philong.order.R;
import com.example.philong.order.activity.FormCustomer;

import java.util.ArrayList;

public class Food_Adapter_Order extends RecyclerView.Adapter<Food_Adapter_Order.ViewHolder> {
    ArrayList<Food> foodOrderList;
   Context context;

    public Food_Adapter_Order(ArrayList<Food> foodArrayList,Context  context) {
        this.foodOrderList = foodArrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item_order, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) { //position vị trí tại click
    holder.cbName.setText(foodOrderList.get(position).getFoodName());
    holder.txtGia.setText(foodOrderList.get(position).getPrice());

    holder.btncong.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int k=Integer.parseInt(holder.txtSoluong.getText().toString()) + 1;

            if(k>9) Toast.makeText(context, "Chọn nhiều quá", Toast.LENGTH_SHORT).show();

            else holder.txtSoluong.setText(String.valueOf(k));  //else thì cho click

        }

    });

    holder.btntru.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int l=Integer.parseInt(holder.txtSoluong.getText().toString()) - 1;

            if (l<0) Toast.makeText(context, "Đùa Hoài", Toast.LENGTH_SHORT).show();

            else holder.txtSoluong.setText(String.valueOf(l));  //else thì cho click
        }
    });



    }



    @Override
    public int getItemCount() {
        return foodOrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cbName,txtGia,txtSoluong;
        Button btncong,btntru;


        public ViewHolder(View itemView) {
            super(itemView);
         cbName=itemView.findViewById(R.id.checkBox_name_order);
         txtGia=itemView.findViewById(R.id.textView_price_order);
         btncong=itemView.findViewById(R.id.button_cong);
         txtSoluong=itemView.findViewById(R.id.textview_soluong_order1);
         btntru=itemView.findViewById(R.id.button_tru);

        }
    }
}
