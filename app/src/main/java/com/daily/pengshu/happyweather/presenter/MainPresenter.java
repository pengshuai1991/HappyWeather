package com.daily.pengshu.happyweather.presenter;

import android.view.View;

import com.daily.pengshu.happyweather.bean.City;
import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weatherinfo;
import com.daily.pengshu.happyweather.model.ILocation;
import com.daily.pengshu.happyweather.model.IWeatherData;
import com.daily.pengshu.happyweather.model.LocationModel;
import com.daily.pengshu.happyweather.model.WeatherDataModel;
import com.daily.pengshu.happyweather.view.IMainView;

/**
 * Created by pengshu on 16/7/17.
 */
public class MainPresenter {

    private IMainView mIMainView;
    private IWeatherData mIweatherData;
    private ILocation mIlocaitn;

    public MainPresenter(IMainView v) {
        mIMainView = v;
        mIweatherData = new WeatherDataModel();
        mIlocaitn = new LocationModel();


    }

    public SimpleWeatherinfo getSimpleWeatherInfo() {

        return mIweatherData.getCurSimpleWeatherInfo();


    }

   public Pm2d5 getPm2d5(){
      return mIweatherData.getCurPm2d5();
   }

    public Weatherinfo getWeatherInfo(){
        return mIweatherData.getCurWeatherInfo();
    }

    public City getCurCity(){
       return  mIlocaitn.getCurCity();
    }

}
