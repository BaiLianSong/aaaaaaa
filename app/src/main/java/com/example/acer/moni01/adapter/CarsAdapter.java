package com.example.acer.moni01.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.acer.moni01.R;
import com.example.acer.moni01.bean.UserBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by acer on 2018/8/21.
 */

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {

    private Context context;
    private List<UserBean.DataBean> list;

    public CarsAdapter(Context context, List<UserBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item,parent,false);
        CarViewHolder viewHolder = new CarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        UserBean.DataBean listBean = list.get(position);
        holder.shopHome_text.setText(listBean.getSellerid());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.car_item_xr.setLayoutManager(layoutManager);
        holder.car_item_xr.setAdapter(new Cars_item_Adapter(context,listBean.getList()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        private CheckBox shopCheck;
        private TextView shopHome_text;
        private XRecyclerView car_item_xr;
        public CarViewHolder(View itemView) {
            super(itemView);
            shopCheck = itemView.findViewById(R.id.shopCheck);
            shopHome_text = itemView.findViewById(R.id.shopHome_text);
            car_item_xr = itemView.findViewById(R.id.car_item_xr);
        }
    }
}
