package com.daily.pengshu.happyweather.model;

import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeather;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weatherinfo;

/**
 * Created by pengshu on 16/7/17.
 */
public interface IWeatherData {
    String getSimpleWeatherStr();
    SimpleWeatherinfo getCurSimpleWeatherInfo();
    Weatherinfo getCurWeatherInfo();
    Pm2d5 getCurPm2d5();
}
