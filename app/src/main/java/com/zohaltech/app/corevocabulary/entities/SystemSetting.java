package com.zohaltech.app.corevocabulary.entities;


import com.zohaltech.app.corevocabulary.classes.Helper;

public class SystemSetting {
    private int     id;
    private Boolean installed;
    private Boolean premium;
    private String premiumVersion;

    public SystemSetting(Boolean installed,Boolean premium, String premiumVersion) {
        setInstalled(installed);
        setPremium(premium);
        setPremiumVersion(premiumVersion);
    }

    public SystemSetting(int id, Boolean installed,Boolean premium, String premiumVersion) {
        this(installed, premium, premiumVersion);
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

    public String getPremiumVersion() {
        return premiumVersion;
    }

    public void setPremiumVersion(String premiumVersion) {
        this.premiumVersion = premiumVersion;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public boolean isPremium(){
        return getPremiumVersion() != null && getPremiumVersion().equals(Helper.hashString(Helper.getDeviceId()));
    }
}
