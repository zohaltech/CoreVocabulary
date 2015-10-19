package com.zohaltech.app.corevocabulary.classes;

import android.os.Build;
import android.util.Log;

import com.zohaltech.app.corevocabulary.BuildConfig;
import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.data.SystemSettings;
import com.zohaltech.app.corevocabulary.entities.SystemSetting;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebApiClient {
    private static final String HOST_URL = App.context.getString(R.string.host_name);

    public enum PostAction {
        INSTALL,
        REGISTER
    }

    public static void sendUserData(final PostAction action, final String token) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SystemSetting setting = SystemSettings.getCurrentSettings();
                    JSONObject jsonObject = new JSONObject();

                    if (action == PostAction.INSTALL) {
                        if (!setting.getInstalled()) {
                            if (ConnectionManager.getInternetStatus() == ConnectionManager.InternetStatus.Connected) {
                                jsonObject.accumulate("SecurityKey", ConstantParams.getApiSecurityKey());
                                jsonObject.accumulate("AppId", 2);
                                jsonObject.accumulate("DeviceId", Helper.getDeviceId());
                                jsonObject.accumulate("DeviceBrand", Build.MANUFACTURER);
                                jsonObject.accumulate("DeviceModel", Build.MODEL);
                                jsonObject.accumulate("AndroidVersion", Build.VERSION.RELEASE);
                                jsonObject.accumulate("ApiVersion", Build.VERSION.SDK_INT);
                                jsonObject.accumulate("OperatorId", Helper.getOperator().ordinal());
                                jsonObject.accumulate("IsPurchased", false);
                                jsonObject.accumulate("MarketId", App.market);
                                jsonObject.accumulate("AppVersion", BuildConfig.VERSION_CODE);
                                jsonObject.accumulate("PurchaseToken", token);
                                Boolean result = post(jsonObject);
                                if (result) {
                                    setting.setInstalled(true);
                                    SystemSettings.update(setting);
                                }
                            }
                        }
                    } else {
                        if (!setting.getPremium()) {
                            if (ConnectionManager.getInternetStatus() == ConnectionManager.InternetStatus.Connected) {
                                jsonObject.accumulate("SecurityKey", ConstantParams.getApiSecurityKey());
                                jsonObject.accumulate("AppId", 2);
                                jsonObject.accumulate("DeviceId", Helper.getDeviceId());
                                jsonObject.accumulate("DeviceBrand", Build.MANUFACTURER);
                                jsonObject.accumulate("DeviceModel", Build.MODEL);
                                jsonObject.accumulate("AndroidVersion", Build.VERSION.RELEASE);
                                jsonObject.accumulate("ApiVersion", Build.VERSION.SDK_INT);
                                jsonObject.accumulate("OperatorId", Helper.getOperator().ordinal());
                                jsonObject.accumulate("IsPurchased", true);
                                jsonObject.accumulate("MarketId", App.market);
                                jsonObject.accumulate("AppVersion", BuildConfig.VERSION_CODE);
                                jsonObject.accumulate("PurchaseToken", token);
                                Boolean result = post(jsonObject);
                                if (result) {
                                    setting.setPremium(true);
                                    SystemSettings.update(setting);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    private static Boolean post(JSONObject jsonObject) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(HOST_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            return urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK;

        } catch (MyRuntimeException | IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return false;
    }
}
