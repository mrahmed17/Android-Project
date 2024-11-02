package com.example.QuizApp;

import static com.example.QuizApp.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    TextView userName, btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        btnLogout = findViewById(id.btnLogout);
        userName = findViewById(id.tvUsernameHome);
        userName.setText("হ্যালো " + name.toUpperCase());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                alertDialogBuilder.setTitle("আপনার অ্যাকাউন্ট থেকে লগ আউট!");
                alertDialogBuilder.setIcon(R.drawable.question);
                alertDialogBuilder.setMessage("আপনি কি নিশ্চিত?");
                alertDialogBuilder.setCancelable(false);

                alertDialogBuilder.setPositiveButton("নিশ্চিত করুন", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int argl) {
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("বাতিল করুন", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialogBuilder.create().show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Notify.exit(HomeActivity.this);
    }



    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent;

        if (viewId == R.id.ltw) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 1);
            intent.putExtra("categoryName", "ওয়েব প্রোগ্রামিং");
            startActivity(intent);
        } else if (viewId == R.id.hdt) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 2);
            intent.putExtra("categoryName", "অবজেক্ট ওরিয়েন্টেড");
            startActivity(intent);
        } else if (viewId == R.id.android) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 3);
            intent.putExtra("categoryName", "অ্যান্ড্রয়েড প্রোগ্রামিং");
            startActivity(intent);
        } else if (viewId == R.id.web) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 4);
            intent.putExtra("categoryName", "নেটওয়ার্ক অ্যাডমিন");
            startActivity(intent);
        } else if (viewId == R.id.hdh) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 5);
            intent.putExtra("categoryName", "অপারেটিং সিস্টেম");
            startActivity(intent);
        } else if (viewId == R.id.sql) {
            intent = new Intent(HomeActivity.this, StartActivity.class);
            intent.putExtra("categoryId", 6);
            intent.putExtra("categoryName", "ডাটাবেস ম্যানেজমেন্ট সিস্টেম");
            startActivity(intent);
        } else {
            Toast.makeText(HomeActivity.this, "a", Toast.LENGTH_SHORT).show();
        }
    }

}