package com.example.acer.moni01.view;

import com.example.acer.moni01.bean.UserBean;

/**
 * Created by acer on 2018/8/21.
 */

public interface CarView {
    //请求成功
    void success(UserBean bean);
    //请求失败
    void failure(String s);
}
