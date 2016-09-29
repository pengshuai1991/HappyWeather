package com.daily.pengshu.happyweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daily.pengshu.happyweather.R;
import com.daily.pengshu.happyweather.adapter.WeatherPagerAdapter;
import com.daily.pengshu.happyweather.bean.City;
import com.daily.pengshu.happyweather.bean.Pm2d5;
import com.daily.pengshu.happyweather.bean.SimpleWeatherinfo;
import com.daily.pengshu.happyweather.bean.Weatherinfo;
import com.daily.pengshu.happyweather.presenter.MainPresenter;
import com.daily.pengshu.happyweather.util.T;
import com.daily.pengshu.happyweather.view.fragment.FirstWeatherFragment;
import com.daily.pengshu.happyweather.view.fragment.SecondWeatherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener,IMainView {


    private City mCurCity;
    private Weatherinfo mCurWeatherinfo;
    private SimpleWeatherinfo mCurSimpleWeatherinfo;
    private Pm2d5 mCurPm2d5;
    private City mNewIntentCity;


    private WeatherPagerAdapter mWeatherPagerAdapter;
    private ImageView mCityManagerBtn, mUpdateBtn, mLocationBtn, mShareBtn;
    private ProgressBar mUpdateProgressBar;
    private TextView mTitleTextView;
    private TextView cityTv, timeTv, humidityTv, weekTv, pmDataTv, pmQualityTv,
            temperatureTv, climateTv, windTv;
    private ImageView weatherImg, pmImg;;
    private ViewPager mViewPager;
    private List<Fragment> fragments;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initData() {
        mainPresenter = new MainPresenter(this);
        mCurSimpleWeatherinfo = mainPresenter.getSimpleWeatherInfo();
        T.show(this,mCurSimpleWeatherinfo.getCity(),Toast.LENGTH_SHORT);
        if(mCurSimpleWeatherinfo != null)
        System.out.println(mCurSimpleWeatherinfo.getCity()+"--------");


        mCurCity = mainPresenter.getCurCity();
        T.show(this,mCurCity.getProvince(),Toast.LENGTH_SHORT);
        if(mCurCity !=null )
        System.out.println(mCurCity.getCity()+"--------");


        mCurWeatherinfo=mainPresenter.getWeatherInfo();
        if(mCurWeatherinfo != null)
            System.out.println(mCurWeatherinfo.getCity());

        mCurPm2d5 = mainPresenter.getPm2d5();
        if (mCurPm2d5 != null) {
            System.out.println(mCurPm2d5.getAqi());
        }

    }

    private void initView() {
        mCityManagerBtn = (ImageView) findViewById(R.id.title_city_manager);
        mCityManagerBtn.setOnClickListener(this);

        mUpdateBtn = (ImageView) findViewById(R.id.title_update_btn);
        mShareBtn = (ImageView) findViewById(R.id.title_share);
        mLocationBtn = (ImageView) findViewById(R.id.title_location);
        mCityManagerBtn.setOnClickListener(this);
        mUpdateBtn.setOnClickListener(this);
        mShareBtn.setOnClickListener(this);
        mLocationBtn.setOnClickListener(this);
        mShareBtn.setVisibility(View.GONE);
        mUpdateProgressBar = (ProgressBar) findViewById(R.id.title_update_progress);
        mTitleTextView = (TextView) findViewById(R.id.title_city_name);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        cityTv = (TextView) findViewById(R.id.city);
        timeTv = (TextView) findViewById(R.id.time);
        humidityTv = (TextView) findViewById(R.id.humidity);
        weekTv = (TextView) findViewById(R.id.week_today);
        pmDataTv = (TextView) findViewById(R.id.pm_data);
        pmQualityTv = (TextView) findViewById(R.id.pm2_5_quality);
        pmImg = (ImageView) findViewById(R.id.pm2_5_img);
        temperatureTv = (TextView) findViewById(R.id.temperature);
        climateTv = (TextView) findViewById(R.id.climate);
        windTv = (TextView) findViewById(R.id.wind);
        weatherImg = (ImageView) findViewById(R.id.weather_img);

        fragments = new ArrayList<Fragment>();
        fragments.add(new FirstWeatherFragment());
        fragments.add(new SecondWeatherFragment());

        mWeatherPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mWeatherPagerAdapter);





    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.title_city_manager:
                Intent i = new Intent(this,SelectCityActivity.class);
                startActivityForResult(i,0);
        }

    }

    @Override
    public void setCurCity(City city) {
       this.mCurCity = city;

    }

    @Override
    public void setCurPm2d5(Pm2d5 pm2d5) {
        this.mCurPm2d5 = pm2d5;
    }

    @Override
    public void setCurWeatherInfo(Weatherinfo weatherInfo) {
        this.mCurWeatherinfo = weatherInfo;
    }

    @Override
    public void setCurSampleWeatherInfo(SimpleWeatherinfo sampleWeatherInfo) {
        this.mCurSimpleWeatherinfo = sampleWeatherInfo;
    }

    @Override
    public City getnewIntentCity() {
        return null;
    }
}
