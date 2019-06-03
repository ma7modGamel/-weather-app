package com.xx.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class homeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        MainActivity activity= new MainActivity();
        if(activity.flagCheck==false){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, ""+activity.flagCheck, Toast.LENGTH_SHORT).show();
        }

    }
}
