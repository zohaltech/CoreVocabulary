package com.zohaltech.app.corevocabulary.classes;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    // private final Activity myContext;
    private static final String LINE_SEPARATOR = "\n";

    public MyUncaughtExceptionHandler(Activity context) {
        // myContext = context;
    }

    public void uncaughtException(Thread thread, final Throwable exception) {
        logException(exception);
    }

    public static void logException(final Throwable exception) {
        new Thread(new Runnable() {
            public void run() {
                StringBuilder errorReport = new StringBuilder();
                StringWriter stackTrace = new StringWriter();
                exception.printStackTrace(new PrintWriter(stackTrace));
                errorReport.append("************ CAUSE OF ERROR ************\n\n");
                errorReport.append(stackTrace.toString());
                //errorReport.append("\n************ APP INFORMATION ***********\n");
                //errorReport.append("Version Code: ");
                //errorReport.append(BuildConfig.VERSION_CODE);
                //errorReport.append("\n************ DEVICE INFORMATION ***********\n");
                //errorReport.append("Brand: ");
                //errorReport.append(Build.BRAND);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Device: ");
                //errorReport.append(Build.DEVICE);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Model: ");
                //errorReport.append(Build.MODEL);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Id: ");
                //errorReport.append(Build.ID);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Product: ");
                //errorReport.append(Build.PRODUCT);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("\n************ FIRMWARE ************\n");
                //errorReport.append("SDK_INT: ");
                //errorReport.append(Build.VERSION.SDK_INT);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Release: ");
                //errorReport.append(Build.VERSION.RELEASE);
                //errorReport.append(LINE_SEPARATOR);
                //errorReport.append("Incremental: ");
                //errorReport.append(Build.VERSION.INCREMENTAL);
                //errorReport.append(LINE_SEPARATOR);
                errorReport.append("--------------------------------------------------\n");

                //                G.handler.post(new Runnable() {
                //                    public void run() {
                //                        final Intent intent = new Intent(G.currentActivity, ErrorActivity.class);
                //                        intent.putExtra(ErrorActivity.ERROR_TEXT, errorReport.toString());
                //                        G.currentActivity.startActivity(intent);
                //                        Toast.makeText(G.context, errorReport.toString(), Toast.LENGTH_LONG).show();
                //                    }
                //                });

                writeToFile(errorReport.toString());
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(10);
            }
        }).start();
    }

    private static void writeToFile(String errorText)
    {
        try
        {
            File file = new File(Environment.getExternalStorageDirectory(), "mobiledatamonitor_log.txt");
            if (!file.exists())
            {
                // file.mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.append(errorText);
            writer.flush();
            writer.close();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
    }

}
