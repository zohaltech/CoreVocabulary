package com.zohaltech.app.corevocabulary.classes;


import android.os.Environment;

import com.zohaltech.app.corevocabulary.R;

public final class ConstantParams {
    private static String licenseFilePath = Environment.getExternalStorageDirectory() + App.context.getString(R.string.topol);
    private static String apiSecurityKey  = App.context.getString(R.string.jan);
    private static String fileName        = App.context.getString(R.string.doosh);
    private static String secretKey       = App.context.getString(R.string.sdj);
    private static String iv              = App.context.getString(R.string.mongol);
    private static String seven           = App.context.getString(R.string.goon);
    private static String bazaarPublicKey = App.context.getString(R.string.bare_sala);
    private static String candoPublicKey  = App.context.getString(R.string.nice_ass);
    private static String myketPublicKey  = App.context.getString(R.string.doozlu_bala);
    private static String playPublicKey   = App.context.getString(R.string.bare_sala);

    public static String getLicenseFilePath() {
        return licenseFilePath;
    }

    public static String getFileName() {
        return fileName;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static String getIv() {
        return iv;
    }

    public static String getSeven() {
        return seven;
    }

    public static String getBazaarPublicKey() {
        return bazaarPublicKey;
    }

    public static String getCandoPublicKey() {
        return candoPublicKey;
    }

    public static String getMyketPublicKey() {
        return myketPublicKey;
    }

    public static String getPlayPublicKey() {
        return playPublicKey;
    }

    public static String getApiSecurityKey() {
        return apiSecurityKey;
    }
}
