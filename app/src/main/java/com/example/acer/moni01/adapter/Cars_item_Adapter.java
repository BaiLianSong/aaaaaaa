package com.example.acer.moni01.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.moni01.R;
import com.example.acer.moni01.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by acer on 2018/8/21.
 */

public class Cars_item_Adapter extends RecyclerView.Adapter<Cars_item_Adapter.CarViewHolder> {

    private Context context;
    private List<UserBean.DataBean.ListBean> list;

    public Cars_item_Adapter(Context context, List<UserBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item_layout,parent,false);
        CarViewHolder viewHolder = new CarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        UserBean.DataBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] imgs = images.split("\\|");
        Glide.with(context).load(imgs[0]).into(holder.car_item_layout_image);
        holder.car_item_layout_text01.setText(listBean.getSubhead());
        holder.car_item_layout_text02.setText(listBean.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        private CheckBox car_item_layout_check;
        private TextView car_item_layout_text01,car_item_layout_text02;
        private ImageView car_item_layout_image;
        public CarViewHolder(View itemView) {
            super(itemView);
            car_item_layout_check = itemView.findViewById(R.id.car_item_layout_checkbox);
            car_item_layout_text01 = itemView.findViewById(R.id.preson_text01);
            car_item_layout_text02 = itemView.findViewById(R.id.preson_text02);
            car_item_layout_image = itemView.findViewById(R.id.car_item_layout_image);
        }
    }
}
