package com.daily.pengshu.happyweather.view;

import android.view.View;

import com.daily.pengshu.happyweather.bean.City;
import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weatherinfo;

/**
 * Created by pengshu on 16/7/17.
 */
public interface IMainView {

    void setCurCity(City city);
    void setCurPm2d5(Pm2d5 pm2d5);
    void setCurWeatherInfo(Weatherinfo weatherInfo);
    void setCurSampleWeatherInfo(SimpleWeatherinfo sampleWeatherInfo);

    City getnewIntentCity();


}
