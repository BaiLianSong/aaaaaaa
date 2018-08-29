package com.example.acer.moni01;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.acer.moni01.adapter.CarsAdapter;
import com.example.acer.moni01.bean.UserBean;
import com.example.acer.moni01.presenter.CarPresenter;
import com.example.acer.moni01.utils.ApiUtil;
import com.example.acer.moni01.view.CarView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends Activity implements CarView{
    private CarPresenter carPresenter;
    private XRecyclerView xr;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        HashMap<String,String> parms = new HashMap();
        parms.put("uid","71");


        carPresenter = new CarPresenter(this);
        carPresenter.apply(ApiUtil.API_URL,parms);
        carPresenter.detachp();
    }

    private void initView() {
        xr = findViewById(R.id.xr);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        xr.setLayoutManager(layoutManager);//布局管理器

    }

    @Override
    public void success(final UserBean bean) {
        new Runnable(){
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,bean.getData().size(),Toast.LENGTH_SHORT).show();
            }
        };
        xr.setAdapter(new CarsAdapter(MainActivity.this,bean.getData()));

    }

    @Override
    public void failure(final String s) {
        new Runnable(){
            @Override
            public void run() {
                Log.e("吐司=================",s);
            }
        };
    }
}
