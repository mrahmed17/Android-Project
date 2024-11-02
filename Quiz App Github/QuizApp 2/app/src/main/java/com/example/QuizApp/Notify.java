package com.example.QuizApp;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Notify {
    public static void exit (Context context){
        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(context);
        alerDialogBuilder.setTitle("প্রস্থান নিশ্চিত করুন!");
        alerDialogBuilder.setIcon(R.drawable.question);
        alerDialogBuilder.setMessage("আপনি কি প্রস্থান করতে চান?");
        alerDialogBuilder.setCancelable(false);

        alerDialogBuilder.setPositiveButton("নিশ্চিত করুন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int argl) {
                System.exit(1);
            }
        });

        alerDialogBuilder.setNegativeButton("বাতিল করুন", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alerDialogBuilder.create().show();
    }
}
