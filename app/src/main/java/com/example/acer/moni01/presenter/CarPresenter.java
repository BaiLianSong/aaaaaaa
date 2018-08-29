package com.example.acer.moni01.presenter;

import com.example.acer.moni01.bean.UserBean;
import com.example.acer.moni01.model.CarModel;
import com.example.acer.moni01.view.CarView;

import java.util.HashMap;

/**
 * Created by acer on 2018/8/21.
 */

public class CarPresenter {
    private CarView carView;
    private CarModel carModel;
    public CarPresenter(CarView carView) {
        attachp(carView);
        this.carModel = new CarModel();
    }

    //绑定的方法
    public void attachp(CarView carView){
        this.carView = carView;
    }

    //接触绑定的方法
    public void detachp(){
        this.carView = null;
    }
    public void apply(String url, HashMap<String,String> parms){
        carModel.getData(url,parms, new CarModel.ApplyCallBack() {
            @Override
            public void success(UserBean userBean) {
                carView.success(userBean);

            }

            @Override
            public void failure(String s) {
                carView.failure(s);
            }
        });
    }
}
