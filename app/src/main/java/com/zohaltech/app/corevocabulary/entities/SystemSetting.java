package com.zohaltech.app.corevocabulary.entities;


import com.zohaltech.app.corevocabulary.classes.Helper;

public class SystemSetting {
    private int     id;
    private Boolean installed;
    private String  premium;

    public SystemSetting(Boolean installed, String premium) {
        setInstalled(installed);
        setPremium(premium);
    }

    public SystemSetting(int id, Boolean installed, String premium) {
        this(installed, premium);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getInstalled() {
        return installed;
    }

    public void setInstalled(Boolean installed) {
        this.installed = installed;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public boolean isPremium(){
        return getPremium() != null && getPremium().equals(Helper.hashString(Helper.getDeviceId()));
    }
}
