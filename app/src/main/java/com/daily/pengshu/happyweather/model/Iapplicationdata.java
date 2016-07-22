package com.daily.pengshu.happyweather.model;

import com.daily.pengshu.happyweather.util.SharePreferenceUtil;

import java.util.Map;

/**
 * Created by pengshu on 16/7/19.
 */
public interface Iapplicationdata {

    public Map<String,Integer> getWeatherIcon();
    public Map<String,Integer> getWidgetWeatherIcon();
    public  SharePreferenceUtil getSharepreferenceUtil();



}
