package com.hoangtuan.scanqrc.Model;

import java.io.Serializable;

/**
 * Created by atbic on 30/3/2018.
 */

public class HistoryFindModel implements Serializable {
    int id;
    String url;

    String name;
    String mota;
    public HistoryFindModel() {
    }

    public HistoryFindModel(String url, String name, String mota) {
        this.url = url;
        this.name = name;
        this.mota = mota;
    }

    public HistoryFindModel(int id, String url, String name, String mota) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HistoryFindModel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", mota='" + mota + '\'' +
                '}';
    }
}
