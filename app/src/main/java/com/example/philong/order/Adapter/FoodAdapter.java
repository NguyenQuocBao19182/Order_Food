package com.example.philong.order.Adapter;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.philong.order.Object.Food;
import com.example.philong.order.activity.MainActivity;
import com.example.philong.order.R;
import com.example.philong.order.activity.Update;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    List<Food> foodList;
    private MainActivity context;
    private int layout;

    public FoodAdapter(MainActivity context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    public  class ViewHolder{
        TextView cbNameFood;
        TextView txtPrice;
        ImageView imgUpdate;
        ImageView imgDelete;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private void XacNhanXoa(String tenMon, final int id){

        AlertDialog.Builder dialogxoa=new AlertDialog.Builder(context);
        dialogxoa.setMessage("Ban co muon xoa mon : "+tenMon+" khong");
        dialogxoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.DeleteFood(id);
            }
        });
        dialogxoa.setNegativeButton("khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogxoa.show();
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(layout,null);

            holder.cbNameFood=convertView.findViewById(R.id.checkBox_name);
            holder.txtPrice=convertView.findViewById(R.id.textView_price);
            holder.imgUpdate=convertView.findViewById(R.id.img_edit);
            holder.imgDelete=convertView.findViewById(R.id.img_delete);

            convertView.setTag(holder); // nếu else thì giữ ánh xạ vào biến holder


        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        final Food food=foodList.get(position);



        holder.cbNameFood.setText(food.getFoodName());
        holder.txtPrice.setText(food.getPrice());



        holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Update.class);
                intent.putExtra("dataFood",food);
                context.startActivity(intent);

            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XacNhanXoa(food.getFoodName(),food.getID());
            }
        });
        return convertView;
    }






}
