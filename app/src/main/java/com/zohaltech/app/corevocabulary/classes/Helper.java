package com.zohaltech.app.corevocabulary.classes;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.TelephonyManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class Helper {

    public enum Operator {
        MCI,
        IRANCELL,
        RIGHTELL,
        NO_SIM
    }

    public static Operator getOperator() {
        Operator operator = Operator.NO_SIM;
        try {
            TelephonyManager tm = (TelephonyManager) App.context.getSystemService(Context.TELEPHONY_SERVICE);
            String simOperatorName = tm.getSimOperatorName().toUpperCase();
            if (simOperatorName.toUpperCase().compareTo("IR-MCI") == 0 || simOperatorName.compareTo("IR-TCI") == 0) {
                operator = Operator.MCI;
            } else if (simOperatorName.toUpperCase().compareTo("RIGHTEL") == 0) {
                operator = Operator.RIGHTELL;
            } else if (simOperatorName.toUpperCase().compareTo("IRANCELL") == 0) {
                operator = Operator.IRANCELL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return operator;
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String addDay(int day) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return dateFormat.format(cal.getTime());

    }

    public static Date getDateTime(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getDate(String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public static String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) App.context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static void goToWebsite(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        App.currentActivity.startActivity(browserIntent);
    }

    public static void playSound() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(App.context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void vibrate() {
        Vibrator vibrator = (Vibrator) App.context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }

    public static int indexOf(Object o, ArrayList<Object> elementData) {
        if (o == null) {
            for (int i = 0; i < elementData.size(); i++)
                if (elementData.get(i) == null)
                    return i;
        } else {
            for (int i = 0; i < elementData.size(); i++)
                if (o.equals(elementData.get(i)))
                    return i;
        }
        return -1;
    }

    public static String hashString(String plaintext){
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
