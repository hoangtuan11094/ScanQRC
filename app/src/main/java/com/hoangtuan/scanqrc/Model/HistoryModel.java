package com.hoangtuan.scanqrc.Model;

/**
 * Created by atbic on 29/3/2018.
 */

public class HistoryModel {
    int id;
    int imgStyleCode;
    String styleCode;
    String content;

    public HistoryModel() {
    }

    public HistoryModel(int imgStyleCode, String styleCode, String content) {
        this.imgStyleCode = imgStyleCode;
        this.styleCode = styleCode;
        this.content = content;
    }

    public HistoryModel(int id, int imgStyleCode, String styleCode, String content) {
        this.id = id;
        this.imgStyleCode = imgStyleCode;
        this.styleCode = styleCode;
        this.content = content;
    }

    public int getImgStyleCode() {
        return imgStyleCode;
    }

    public void setImgStyleCode(int imgStyleCode) {
        this.imgStyleCode = imgStyleCode;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HistoryModel{" +
                "imgStyleCode=" + imgStyleCode +
                ", styleCode='" + styleCode + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
