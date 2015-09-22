package com.zohaltech.app.corevocabulary.classes;


public class LicenseStatus {
    private String appVersion;
    private String deviceId;
    private String installDate;
    private int    status;
    private int    checkCount;

    public LicenseStatus(String appVersion, String deviceId, String installDate, int status, int checkCount) {
        setAppVersion(appVersion);
        setDeviceId(deviceId);
        setInstallDate(installDate);
        setStatus(status);
        setCheckCount(checkCount);
    }

    public LicenseStatus() {
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }
}
