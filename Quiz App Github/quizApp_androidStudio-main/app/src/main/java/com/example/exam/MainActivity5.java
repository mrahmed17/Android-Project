package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {
    RadioButton a1,a2,a3,a4;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);

        btn=findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if  (wrong()) {
                    Toast.makeText(MainActivity5.this, "Right answer", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity5.this, MainActivity6.class);
                    startActivity(i);
                }
                else{
                    Intent j=new Intent(MainActivity5.this, MainActivity7.class);
                    startActivity(j);
                }
            }
        });


    }

    private Boolean wrong(){
        if (!a4.isChecked()){
            return false;
        }
        return true;
    }


}