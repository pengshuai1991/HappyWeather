package com.daily.pengshu.happyweather.model;

import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeather;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weather;
import com.daily.pengshu.happyweather.bean.Weatherinfo;
import com.daily.pengshu.happyweather.config;
import com.daily.pengshu.happyweather.util.T;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pengshu on 16/7/17.
 */
public class WeatherDataModel implements IWeatherData{

    private SimpleWeatherinfo simpleWeatherinfo;//定义一个类内部全局的引用
    private SimpleWeather simpleWeather;
    private Weatherinfo mWeatherInfo;
    private Gson mGson;
    private String testStr  = "{\"mSimpleweatherinfo\":{\"city\":\"杭州\",\"cityid\":\"101110101\",\"temp\":\"20\",\"WD\":\"36℃\",\"WS\":\"正常\",\"SD\":\"14%\",\"WSE\":\"1\",\"time\":\"17:00\"}}";
    private Pm2d5 mPm2d5;
    private Weather mWeather;


    //在构造函数中创建Gson对象
    public WeatherDataModel(){
        mGson = new Gson();

    }

    //URLConnection网络链接工具  目前有点问题 需要单独开启一个线程才能发起网络请求 该用okhttp
    public String sendSms() throws Exception {
        String path = config.TEMP_WEATHER_API;
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        String result = convertStreamToString(inStream);
        System.out.println(result);
        return  result;

    }


    //inputStream 转成  String 字符串
    public String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder sb = new StringBuilder();


        String line = null;

        try {

            while ((line = reader.readLine()) != null) {

                sb.append(line + "/n");

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                is.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }


        return sb.toString();

    }

    //网络链接获取Weather的gson字符串
    @Override
    public String getSimpleWeatherStr(){
        String resultstr  = null;
        try {
            resultstr = sendSms();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultstr != null)
        return resultstr;
        else
            return "{\"mSimpleweatherinfo\":{\"city\":\"杭州\",\"cityid\":\"101110101\",\"temp\":\"20\",\"WD\":\"36℃\",\"WS\":\"正常\",\"SD\":\"14%\",\"WSE\":\"1\",\"time\":\"17:00\"}}";
    }

    //网络链接获取Weather的gson 字符串
    public String getWeatherStr(){

        return "{ \"weatherinfo\":\n" +
                "        {\n" +
                "            \"city\":\"北京\",\n" +
                "            \"city_en\":\"beijing\",\n" +
                "            \"date_y\":\"2013年1月17日\",\n" +
                "            \"date\":\"\",\n" +
                "            \"week\":\"星期四\",\n" +
                "            \"fchh\":\"11\",\n" +
                "            \"cityid\":\"101010100\",\n" +
                "            \"temp1\":\"3℃~-8℃\",\n" +
                "            \"temp2\":\"3℃~-5℃\",\n" +
                "            \"temp3\":\"3℃~-3℃\",\n" +
                "            \"temp4\":\"1℃~-5℃\",\n" +
                "            \"temp5\":\"3℃~-6℃\",\n" +
                "            \"temp6\":\"2℃~-5℃\",\n" +
                "            \"tempF1\":\"37.4℉~17.6℉\",\n" +
                "            \"tempF2\":\"37.4℉~23℉\",\n" +
                "            \"tempF3\":\"37.4℉~26.6℉\",\n" +
                "            \"tempF4\":\"33.8℉~23℉\",\n" +
                "            \"tempF5\":\"37.4℉~21.2℉\",\n" +
                "            \"tempF6\":\"35.6℉~23℉\",\n" +
                "            \"weather1\":\"晴\",\n" +
                "            \"weather2\":\"晴\",\n" +
                "            \"weather3\":\"多云转阴\",\n" +
                "            \"weather4\":\"阴转多云\",\n" +
                "            \"weather5\":\"多云转晴\",\n" +
                "            \"weather6\":\"晴转多云\",\n" +
                "            \"img1\":\"0\",\n" +
                "            \"img2\":\"99\",\n" +
                "            \"img3\":\"0\",\n" +
                "            \"img4\":\"99\",\n" +
                "            \"img5\":\"1\",\n" +
                "            \"img6\":\"2\",\n" +
                "            \"img7\":\"2\",\n" +
                "            \"img8\":\"1\",\n" +
                "            \"img9\":\"1\",\n" +
                "            \"img10\":\"0\",\n" +
                "            \"img11\":\"0\",\n" +
                "            \"img12\":\"1\",\n" +
                "            \"img_single\":\"0\",\n" +
                "            \"img_title1\":\"晴\",\n" +
                "            \"img_title2\":\"晴\",\n" +
                "            \"img_title3\":\"晴\",\n" +
                "            \"img_title4\":\"晴\",\n" +
                "            \"img_title5\":\"多云\",\n" +
                "            \"img_title6\":\"阴\",\n" +
                "            \"img_title7\":\"阴\",\n" +
                "            \"img_title8\":\"多云\",\n" +
                "            \"img_title9\":\"多云\",\n" +
                "            \"img_title10\":\"晴\",\n" +
                "            \"img_title11\":\"晴\",\n" +
                "            \"img_title12\":\"多云\",\n" +
                "            \"img_title_single\":\"晴\",\n" +
                "            \"wind1\":\"微风\",\n" +
                "            \"wind2\":\"微风\",\n" +
                "            \"wind3\":\"微风\",\n" +
                "            \"wind4\":\"微风\",\n" +
                "            \"wind5\":\"微风\",\n" +
                "            \"wind6\":\"微风\",\n" +
                "            \"fx1\":\"微风\",\n" +
                "            \"fx2\":\"微风\",\n" +
                "            \"fl1\":\"小于3级\",\n" +
                "            \"fl2\":\"小于3级\",\n" +
                "            \"fl3\":\"小于3级\",\n" +
                "            \"fl4\":\"小于3级\",\n" +
                "            \"fl5\":\"小于3级\",\n" +
                "            \"fl6\":\"小于3级\",\n" +
                "            \"index\":\"冷\",\n" +
                "            \"index_d\":\"天气冷，建议着棉衣、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣或冬大衣。\",\n" +
                "            \"index48\":\"冷\",\n" +
                "            \"index48_d\":\"天气冷，建议着棉衣、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣或冬大衣。\",\n" +
                "            \"index_uv\":\"弱\",\n" +
                "            \"index48_uv\":\"弱\",\n" +
                "            \"index_xc\":\"适宜\",\n" +
                "            \"index_tr\":\"较适宜\",\n" +
                "            \"index_co\":\"较不舒适\",\n" +
                "            \"st1\":\"2\",\n" +
                "            \"st2\":\"-6\",\n" +
                "            \"st3\":\"2\",\n" +
                "            \"st4\":\"-3\",\n" +
                "            \"st5\":\"3\",\n" +
                "            \"st6\":\"-4\",\n" +
                "            \"index_cl\":\"适宜\",\n" +
                "            \"index_ls\":\"基本适宜\",\n" +
                "            \"index_ag\":\"极不易发\"\n" +
                "            }}";
    }

    public String getPm2d5str(){

        return "{\"aqi\": 82,\n" +
                "        \"area\": \"珠海\",\n" +
                "        \"pm2_5\": 31,\n" +
                "        \"pm2_5_24h\": 60,\n" +
                "        \"position_name\": \"吉大\",\n" +
                "        \"primary_pollutant\": \"颗粒物(PM2.5)\",\n" +
                "        \"quality\": \"良\",\n" +
                "        \"station_code\": \"1367A\",\n" +
                "        \"time_point\": \"2013-03-07T19:00:00Z\"}";
    }

    //获取SimpleWeatherinfo 对象
    @Override
    public SimpleWeatherinfo getCurSimpleWeatherInfo() {
        parseSimpleWeatherInfo(testStr);

        return simpleWeatherinfo;
    }

    //获取Weather对象
    @Override
    public Weatherinfo getCurWeatherInfo() {

        parseWeatherInfo(getWeatherStr());

        return mWeatherInfo;
    }

    @Override
    public Pm2d5 getCurPm2d5() {
        parsePm2d5(getPm2d5str());
        return mPm2d5;
    }

    public void parseSimpleWeatherInfo(String str){

        if(mGson != null){
            simpleWeather = mGson.fromJson(str,SimpleWeather.class);
            simpleWeatherinfo = simpleWeather.getWeatherinfo();


            if(simpleWeatherinfo != null)
                System.out.println("a-------------------------------"+simpleWeatherinfo.getCity());

        }
    }

    public void parseWeatherInfo(String str){
        if(mGson != null){

            mWeather = mGson.fromJson(str,Weather.class);
            mWeatherInfo = mWeather.getWeatherinfo();
        }
    }

     public void parsePm2d5(String str){
         if(mGson != null){
             mPm2d5 = mGson.fromJson(str,Pm2d5.class);
         }
     }

}
