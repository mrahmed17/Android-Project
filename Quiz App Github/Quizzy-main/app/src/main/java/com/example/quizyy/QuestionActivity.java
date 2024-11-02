package com.example.quizyy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;

public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Retrieve the country name from the intent
        String country = getIntent().getStringExtra("COUNTRY_NAME");

        // Check if the country name is provided in the intent
        if (country == null) {
            Log.e(TAG, "Country name is missing in the intent extra.");
            finish(); // Finish the activity to avoid further errors
            return;
        }

        Fragment fragment;
        switch (country) {
            case "India":
                fragment = new IndiaFragment();
                break;
            case "USA":
                fragment = new USAFragment();
                break;
            case "Canada":
                fragment = new CanadaFragment();
                break;
            default:
                Log.e(TAG, "Unexpected country value: " + country);
                finish(); // Finish the activity as a fallback for unexpected values
                return;
        }

        // Proceed with loading the appropriate fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerQuestion, fragment)
                .commit();
    }
}
