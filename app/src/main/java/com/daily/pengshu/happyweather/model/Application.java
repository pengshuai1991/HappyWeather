package com.daily.pengshu.happyweather.model;

import android.text.TextUtils;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.daily.pengshu.happyweather.R;
import com.daily.pengshu.happyweather.bean.City;
import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weatherinfo;
import com.daily.pengshu.happyweather.util.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 16/7/19.
 */
public class Application extends android.app.Application implements  Iapplicationdata{
    public static ArrayList<EventHandler> mListeners = new ArrayList<EventHandler>();
    private static String NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final int CITY_LIST_SCUESS = 0;
    private static final String FORMAT = "^[a-z,A-Z].*$";
    private CityDB mCityDB;
    private SharePreferenceUtil getmSpUtil;
    private Map<String, Integer> mWeatherIcon;// 天气图标
    private Map<String, Integer> mWidgetWeatherIcon;// 插件天气图标
    private List<City> mCityList;
    // 首字母集
    private List<String> mSections;
    // 根据首字母存放数据
    private Map<String, List<City>> mMap;
    // 首字母位置集
    private List<Integer> mPositions;
    // 首字母对应的位置
    private Map<String, Integer> mIndexer;
    private boolean isCityListComplite;

    private LocationClient mLocationClient = null;
    private  SharePreferenceUtil mSpUtil;
    private Weatherinfo mCurWeatherinfo;
    private SimpleWeatherinfo mCurSimpleWeatherinfo;
    private Pm2d5 mCurPm2d5;
    public static int mNetWorkState;



    private static Application mApplication;

    public  static  synchronized Application getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    public  void initData() {
        mApplication = this;//直接用this初始化对象
        initWeatherIconMap();
        initWidgetWeather();

        mSpUtil = new SharePreferenceUtil(this,SharePreferenceUtil.CITY_SHAREPRE_FILE);
    }

    public  synchronized  SharePreferenceUtil getSharepreferenceUtil(){
        if(mSpUtil == null){
            mSpUtil = new SharePreferenceUtil(this,SharePreferenceUtil.CITY_SHAREPRE_FILE);
        }

        return mSpUtil;
    }

    private LocationClientOption getLocationClientOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setAddrType("all");
        option.setServiceName(this.getPackageName());
        option.setScanSpan(0);
        option.disableCache(true);
        return option;
    }

    public  synchronized LocationClient getLocationClient(){
        if(mLocationClient == null)
            mLocationClient = new LocationClient(this,getLocationClientOption());

        return mLocationClient;

    }


    private void initWidgetWeather() {
        mWidgetWeatherIcon = new HashMap<String, Integer>();
        mWidgetWeatherIcon.put("暴雪", R.drawable.w17);
        mWidgetWeatherIcon.put("暴雨", R.drawable.w10);
        mWidgetWeatherIcon.put("大暴雨", R.drawable.w10);
        mWidgetWeatherIcon.put("大雪", R.drawable.w16);
        mWidgetWeatherIcon.put("大雨", R.drawable.w9);

        mWidgetWeatherIcon.put("多云", R.drawable.w1);
        mWidgetWeatherIcon.put("雷阵雨", R.drawable.w4);
        mWidgetWeatherIcon.put("雷阵雨冰雹", R.drawable.w19);
        mWidgetWeatherIcon.put("晴", R.drawable.w0);
        mWidgetWeatherIcon.put("沙尘暴", R.drawable.w20);

        mWidgetWeatherIcon.put("特大暴雨", R.drawable.w10);
        mWidgetWeatherIcon.put("雾", R.drawable.w18);
        mWidgetWeatherIcon.put("小雪", R.drawable.w14);
        mWidgetWeatherIcon.put("小雨", R.drawable.w7);
        mWidgetWeatherIcon.put("阴", R.drawable.w2);

        mWidgetWeatherIcon.put("雨夹雪", R.drawable.w6);
        mWidgetWeatherIcon.put("阵雪", R.drawable.w13);
        mWidgetWeatherIcon.put("阵雨", R.drawable.w3);
        mWidgetWeatherIcon.put("中雪", R.drawable.w15);
        mWidgetWeatherIcon.put("中雨", R.drawable.w8);

    }


    private void initWeatherIconMap() {
        mWeatherIcon = new HashMap<String, Integer>();
        mWeatherIcon.put("暴雪", R.drawable.biz_plugin_weather_baoxue);
        mWeatherIcon.put("暴雨", R.drawable.biz_plugin_weather_baoyu);
        mWeatherIcon.put("大暴雨", R.drawable.biz_plugin_weather_dabaoyu);
        mWeatherIcon.put("大雪", R.drawable.biz_plugin_weather_daxue);
        mWeatherIcon.put("大雨", R.drawable.biz_plugin_weather_dayu);

        mWeatherIcon.put("多云", R.drawable.biz_plugin_weather_duoyun);
        mWeatherIcon.put("雷阵雨", R.drawable.biz_plugin_weather_leizhenyu);
        mWeatherIcon.put("雷阵雨冰雹",
                R.drawable.biz_plugin_weather_leizhenyubingbao);
        mWeatherIcon.put("晴", R.drawable.biz_plugin_weather_qing);
        mWeatherIcon.put("沙尘暴", R.drawable.biz_plugin_weather_shachenbao);

        mWeatherIcon.put("特大暴雨", R.drawable.biz_plugin_weather_tedabaoyu);
        mWeatherIcon.put("雾", R.drawable.biz_plugin_weather_wu);
        mWeatherIcon.put("小雪", R.drawable.biz_plugin_weather_xiaoxue);
        mWeatherIcon.put("小雨", R.drawable.biz_plugin_weather_xiaoyu);
        mWeatherIcon.put("阴", R.drawable.biz_plugin_weather_yin);

        mWeatherIcon.put("雨夹雪", R.drawable.biz_plugin_weather_yujiaxue);
        mWeatherIcon.put("阵雪", R.drawable.biz_plugin_weather_zhenxue);
        mWeatherIcon.put("阵雨", R.drawable.biz_plugin_weather_zhenyu);
        mWeatherIcon.put("中雪", R.drawable.biz_plugin_weather_zhongxue);
        mWeatherIcon.put("中雨", R.drawable.biz_plugin_weather_zhongyu);
        System.out.println("initWeatherIconMap");


    }

    public int getWeatherIcon(String climate) {
        int weatherRes = R.drawable.na;
        if (TextUtils.isEmpty(climate))
            return weatherRes;
        String[] strs = { "晴", "晴" };
        if (climate.contains("转")) {// 天气带转字，取前面那部分
            strs = climate.split("转");
            climate = strs[0];
            if (climate.contains("到")) {// 如果转字前面那部分带到字，则取它的后部分
                strs = climate.split("到");
                climate = strs[1];
            }
        }
        if (mWidgetWeatherIcon.containsKey(climate)) {
            weatherRes = mWidgetWeatherIcon.get(climate);
        }
        return weatherRes;
    }

    @Override
    public Map<String, Integer> getWeatherIcon() {
        return mWeatherIcon;
    }

    @Override
    public Map<String, Integer> getWidgetWeatherIcon() {
        return mWidgetWeatherIcon;
    }
}
