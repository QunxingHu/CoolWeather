package com.ustc.hardy.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ustc.hardy.coolweather.model.City;
import com.ustc.hardy.coolweather.model.Country;
import com.ustc.hardy.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Created by QuincyHu on 2016/5/16 0016 10:43.
 * Email:  zhihuqunxing@163.com
 */
public class CoolWeatherDB {
    //DB name
    public static final String DB_NAME = "cool_weather";

    //DB version
    public static final int version = 1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;


    //构造方法私有化
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, version);
        db = dbHelper.getWritableDatabase();
    }

    //获取CoolWeatherDB的实例
    public synchronized static CoolWeatherDB getInstance(Context context){
        if(coolWeatherDB == null)
            coolWeatherDB = new CoolWeatherDB(context);
        return coolWeatherDB;
    }

    //将Province实例存储到数据库
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    //从数据库中读取全国所有的省份信息
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);
            } while (cursor.moveToNext());
        }

        if (cursor != null)
            cursor.close();

        return list;
    }

    //将City实例存储到数据库
    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }

    //从数据库读取某省下所有的城市信息
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?", new String[] {String.valueOf(provinceId)}, null,null, null);
        if(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }

        if (cursor != null)
            cursor.close();

        return list;
    }

    //将Country实例存储到数据库
    public void saveCountry(Country country){
        if(country != null){
            ContentValues values = new ContentValues();
            values.put("country_code", country.getCountryCode());
            values.put("country_name", country.getCountryName());
            values.put("city_id", country.getCityCode());
            db.insert("Country", null, values);
        }
    }

    //从数据库中读取某城市下所有的县信息
    public List<Country> loadCountries(int cityId){
        List<Country> list = new ArrayList<Country>();
        Cursor cursor = db.query("Country", null,"city_id = ?",new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Country country = new Country();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
                country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
                country.setCityCode(cityId);
                list.add(country);
            } while (cursor.moveToNext());
        }

        if(cursor != null)
            cursor.close();

        return list;
    }

}
