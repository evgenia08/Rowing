package com.example.myapplication;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Country implements Serializable
{
    private long ID;
    private String Class;
    private String Distance;
    private String Time;
    private String Date;
    private String Ave500m;
    private String Temp;


    public Country(long ID,String classN, String distance, String time, String ave500m, String date, String temp) {
        this.ID = ID;
        Distance=distance;
        Class=classN;
        Time=time;
        Date=date;
        Ave500m=ave500m;
        Temp=temp;
    }

    public long getID() {
        return ID;
    }

    public String getClas() {return Class;}



    public String getDistance() {
        return Distance;
    }

    public String getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }

    public String getAve500m() {
        return Ave500m;
    }

    public String getTemp() {
        return Temp;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setAve500m(String ave500m) {
        Ave500m = ave500m;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }
    public void setClass(String classN) {
        Class =classN;
    }
}
