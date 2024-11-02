package com.example.quizyy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;

import static androidx.biometric.BiometricManager.Authenticators.*;

public class MainActivity extends AppCompatActivity {

    private Button btnFingerprint;
    private Button btnSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private CheckBox rememberMeCheckBox;

    private static final String PREFS_NAME = "PrefsFile";
    private static final String PREF_REMEMBER_ME = "RememberMe";
    private static final String PREF_USERNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private static final String CORRECT_USERNAME = "user";
    private static final String CORRECT_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFingerprint = findViewById(R.id.btnFingerprint);
        btnSignIn = findViewById(R.id.btnSignIn);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

        checkBioMetricSupported();
        loadPreferences();

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                startHomeActivity();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnFingerprint.setOnClickListener(view -> {
            BiometricPrompt.PromptInfo promptInfo = dialogMetric()
                    .setNegativeButtonText("Cancel")
                    .build();
            biometricPrompt.authenticate(promptInfo);
        });

        btnSignIn.setOnClickListener(view -> {
            if (validateCredentials()) {
                savePreferences();
                startHomeActivity();
            } else {
                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    BiometricPrompt.PromptInfo.Builder dialogMetric() {
        return new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Log in using your biometric credential");
    }

    void checkBioMetricSupported() {
        BiometricManager manager = BiometricManager.from(this);
        switch (manager.canAuthenticate(BIOMETRIC_WEAK | BIOMETRIC_STRONG)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                btnFingerprint.setEnabled(true);
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                btnFingerprint.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Biometric features are not available or set up on this device.", Toast.LENGTH_LONG).show();
                break;
        }
    }

    boolean validateCredentials() {
        String username = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        return username.equals(CORRECT_USERNAME) && password.equals(CORRECT_PASSWORD);
    }

    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (rememberMeCheckBox.isChecked()) {
            editor.putBoolean(PREF_REMEMBER_ME, true);
            editor.putString(PREF_USERNAME, editTextEmail.getText().toString().trim());
            editor.putString(PREF_PASSWORD, editTextPassword.getText().toString().trim());
        } else {
            editor.clear();
        }
        editor.apply();
    }

    private void loadPreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean rememberMe = preferences.getBoolean(PREF_REMEMBER_ME, false);

        if (rememberMe) {
            String savedUsername = preferences.getString(PREF_USERNAME, "");
            String savedPassword = preferences.getString(PREF_PASSWORD, "");
            editTextEmail.setText(savedUsername);
            editTextPassword.setText(savedPassword);
            rememberMeCheckBox.setChecked(true);

            if (savedUsername.equals(CORRECT_USERNAME) && savedPassword.equals(CORRECT_PASSWORD)) {
                startHomeActivity();
            }
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);

        // Start the BackgroundMusicService
        Intent musicServiceIntent = new Intent(MainActivity.this, BackgroundMusicService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startService(musicServiceIntent);
        }
        finish();
    }

}

