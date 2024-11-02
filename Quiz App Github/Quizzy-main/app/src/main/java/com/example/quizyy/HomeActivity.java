package com.example.quizyy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Adding text "Please select your country for the test"
        TextView textView = findViewById(R.id.textViewMessage);
        textView.setText("Please select your country for the test");

        // Adding fragment dynamically
        FragmentManager fragmentManager = getSupportFragmentManager();
        CountriesFragment countriesFragment = new CountriesFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, countriesFragment)
                .commit();


    }
}
