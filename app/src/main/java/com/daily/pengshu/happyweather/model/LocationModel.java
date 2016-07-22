package com.daily.pengshu.happyweather.model;

import com.daily.pengshu.happyweather.bean.City;
import com.google.gson.Gson;

/**
 * Created by pengshu on 16/7/17.
 */
public class LocationModel implements ILocation{

    private Gson mGson;

    private  City mCurCity;//定义一个全局的city对象
    String testStr = "{\"Province\":\"浙江\",\"city\":\"杭州\",\"number\":\"11034\",\"firstPY\":\"H\",\"allPY\":\"HangZhou\",\"allFirstPY\":\"HZ\"}";

    public LocationModel(){
        mGson = new Gson();
    }


    //通过网络链接获取档期的城市的gson字符串
    public String getCitystr(){

        return testStr;
    }

    public City getCurCity( ){

        parseCurCity(getCitystr());

        return mCurCity;
    };

    public void parseCurCity(String str){
        if(mGson!=null){
            mCurCity = mGson.fromJson(str,City.class);
            if(mCurCity != null){
                System.out.println("b------------"+mCurCity.getCity());
            }
        }
    }

}
