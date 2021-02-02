package com.example.myapp.utils;

import android.app.Activity;
import android.app.AlertDialog;

public class ToastAndDialog {
    public static void toast(Activity activity, String msg) {
        android.widget.Toast.makeText(activity, msg,
                android.widget.Toast.LENGTH_SHORT).show();
    }

    public static void dialog (Activity activity, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("הודעה חשובה");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
