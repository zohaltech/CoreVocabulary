package com.zohaltech.app.corevocabulary.entities;


public class SystemSetting {
    private int     id;
    private Boolean installed;
    private Boolean premiumVersion;

    public SystemSetting(Boolean installed, Boolean premiumVersion) {
        setInstalled(installed);
        setPremiumVersion(premiumVersion);
    }

    public SystemSetting(int id, Boolean installed, Boolean premiumVersion) {
        this(installed, premiumVersion);
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

    public Boolean getPremiumVersion() {
        return premiumVersion;
    }

    public void setPremiumVersion(Boolean premiumVersion) {
        this.premiumVersion = premiumVersion;
    }
}
