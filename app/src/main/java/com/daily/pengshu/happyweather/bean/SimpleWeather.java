package com.daily.pengshu.happyweather.bean;

import com.google.gson.annotations.Expose;

public class SimpleWeather {
	@Expose
	private SimpleWeatherinfo mSimpleweatherinfo;

	public SimpleWeatherinfo getWeatherinfo() {
		return mSimpleweatherinfo;
	}

	public void setWeatherinfo(SimpleWeatherinfo weatherinfo) {
		this.mSimpleweatherinfo = weatherinfo;
	}

}
