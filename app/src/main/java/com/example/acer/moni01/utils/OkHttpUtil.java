package com.example.acer.moni01.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by acer on 2018/8/21.
 */

public class OkHttpUtil {
    private static OkHttpUtil okHttpUtil;
    private OkHttpClient okHttpClient;

    private OkHttpUtil() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtil getInstence(){
        if (okHttpUtil==null){
            synchronized (OkHttpUtil.class){
                if (okHttpUtil==null){
                    okHttpUtil = new OkHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public void getData(String url, HashMap<String,String> parms, final RequestCallBack requestCallBack){
        StringBuilder stringBuilder = new StringBuilder();
        String allUrl = "";

        for (Map.Entry<String ,String> stringEntry:parms.entrySet()
             ) {
            stringBuilder.append("?").append(stringEntry.getKey()).append("=").append(stringEntry.getValue()).append("&");
        }
        allUrl = url+stringBuilder.toString().substring(0,stringBuilder.length()-1);

        System.out.println("url"+allUrl);

        Request request = new Request.Builder()
                .url(allUrl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack!=null){
                    requestCallBack.failure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack!=null){
                    requestCallBack.success(call,response);
                }
            }
        });
    }

    //post的封装
    public void postData(String url, HashMap<String,String> parms, final RequestCallBack requestCallBack){
        FormBody.Builder formBody = new FormBody.Builder();
        if (parms!=null&&parms.size()>0){
            for (Map.Entry<String, String> stringStringEntry : parms.entrySet()) {

                formBody.add(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack!=null){
                    requestCallBack.failure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack!=null){
                    requestCallBack.success(call,response);
                }
            }
        });
    }

    public interface RequestCallBack{
        void success(Call call, Response response);
        void failure(Call call, IOException e);
    }
}
