package com.example.acer.moni01.model;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.example.acer.moni01.bean.UserBean;
import com.example.acer.moni01.utils.OkHttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by acer on 2018/8/21.
 */

public class CarModel {
    private Handler handler = new Handler();

    public void getData(String url,HashMap<String,String> parms, final ApplyCallBack applyCallBack){
        OkHttpUtil.getInstence().postData(url, parms, new OkHttpUtil.RequestCallBack() {
            @Override
            public void success(Call call, Response response) {
                try {
                    String string = response.body().string();
                        if (!TextUtils.isEmpty(string)) {
                            //得到字符串后调用解析字符串的方法
                            resultData(string, applyCallBack);
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failure(Call call, IOException e) {
                if (applyCallBack!=null){
                    applyCallBack.failure("网络请求错误");
                }
            }
        });
    }
    //解析字符串的方法
    private void resultData(String string , final ApplyCallBack applyCallBack) {
        final UserBean userBean = new Gson().fromJson(string, UserBean.class);
        if (applyCallBack!=null && userBean!=null){ //标准
            handler.post(new Runnable() {
                @Override
                public void run() {
                    applyCallBack.success(userBean);
                }
            });
//            applyCallBack.success(userBean);
        }
    }

    public interface ApplyCallBack{
        void success(UserBean userBean);
        void failure(String s);
    }
}
