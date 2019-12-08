package com.example.a02;

import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainData {


    private String date;
    private String content;
    private String gns;
    private String gne;
    private String sound;
    private String picture;
    private String period;

    private CheckBox checkBox;
    private boolean checked;


    public MainData(String content, String gns, String gne, String sound, String picture, String period) {
        // 현재시간을 가져와서 format으로 변경
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        this.date = simpleDate.format(mDate);
        this.content = content;
        this.gns = gns;
        this.gne = gne;
        this.sound = sound;
        this.picture = picture;
        this.period = period;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGns() {
        return gns;
    }

    public void setGns(String gns) {
        this.gns = gns;
    }

    public String getGne() {
        return gne;
    }

    public void setGne(String gne) {
        this.gne = gne;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}