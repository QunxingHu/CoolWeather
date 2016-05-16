package com.ustc.hardy.coolweather.model;

/**
 * Author: Created by QuincyHu on 2016/5/16 0016 10:40.
 * Email:  zhihuqunxing@163.com
 */

//县 类
public class Country {
    private int id;
    private String countryName;
    private String countryCode;
    private int cityCode;

    public int getId() {
        return id;
    }

    public int getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
