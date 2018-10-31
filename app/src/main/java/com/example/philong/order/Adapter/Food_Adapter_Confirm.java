package com.example.philong.order.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.philong.order.Object.FoodOrder;
import com.example.philong.order.R;

import java.util.ArrayList;

public class Food_Adapter_Confirm extends RecyclerView.Adapter<Food_Adapter_Confirm.ViewHolder> {
    ArrayList<FoodOrder> foodOrders;
    Context context;

    public Food_Adapter_Confirm(ArrayList<FoodOrder> foodOrders, Context context) {
        this.foodOrders = foodOrders;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item_order, parent, false);
        Food_Adapter_Confirm.ViewHolder viewHolder = new Food_Adapter_Confirm.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
    holder.txtName_conFirm.setText(foodOrders.get(position).getFoodName());
    holder.txtPrice_conFirm.setText(foodOrders.get(position).getPrice());
    holder.txtSize_conFirm.setText(String.valueOf(foodOrders.get(position).getSoluong()));
    }

    @Override
    public int getItemCount() {
        return foodOrders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName_conFirm,txtPrice_conFirm,txtSize_conFirm;
        Button btnAdd_conFirm,btnDelete_conFirm;
        public ViewHolder(View itemView) {
            super(itemView);
            txtName_conFirm=itemView.findViewById(R.id.checkBox_name_order);
            txtPrice_conFirm=itemView.findViewById(R.id.textView_price_order);
            btnAdd_conFirm=itemView.findViewById(R.id.button_cong);
            txtSize_conFirm=itemView.findViewById(R.id.textview_soluong_order1);
            btnDelete_conFirm=itemView.findViewById(R.id.button_tru);

        }
    }
}
