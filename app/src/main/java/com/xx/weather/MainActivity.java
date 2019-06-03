package com.xx.weather;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String[] typeSpeedWind;
    static boolean flagCheck;
    TextView kmph_tv;
    LinearLayout layoutWinder;
    Switch  Temp_switch,
            FormatDaySwitch,
            StateBarSwitch,
            Notf_swirch;
    AlertDialog alertDialog = null;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpWidget();
        initialWidget();
        layoutWinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrefrances();
            }
        });
    }

    private void setUpWidget() {
        kmph_tv = findViewById(R.id.kmph_tv);
        layoutWinder = findViewById(R.id.changeWind);
        Notf_swirch = findViewById(R.id.switchNotif);
        StateBarSwitch = findViewById(R.id.switchStateBar);
        Temp_switch = findViewById(R.id.switchValTemp);
        FormatDaySwitch = findViewById(R.id.switshValTime);
    }

    private void initialWidget() {

            typeSpeedWind = new String[]{"mph", "km/h", "m/s"};
            flagCheck = true;
            kmph_tv.setText("Km/h");
            Notf_swirch.setChecked(false);
            FormatDaySwitch.setChecked(false);
            Temp_switch.setChecked(false);
            StateBarSwitch.setChecked(false);

    }

    private void displayPrefrances() {
        builder = new AlertDialog.Builder(this);
        showAlertDialog();
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void showAlertDialog() {
        builder.setTitle("Choose wind speed unit :")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                })
                .setSingleChoiceItems(typeSpeedWind, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        kmph_tv.setText(typeSpeedWind[which]);

                    }
                }).
                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == -1) {

                        } else {
                            kmph_tv.setText(typeSpeedWind[which]);
                            finish();
                        }
                    }
                });
    }

    boolean val_flag,
            tempSwitchChecked,
            formatDaySwitchChecked,
            stateBarSwitchChecked,
            notfSwirchChecked;
    String val_kmph;
    SharedPreferences preferences;
    public void saveSherdPrefrances(View view) {
        preferences=getSharedPreferences("myFiles",MODE_PRIVATE);
        getValues();
        saveValuesinSherdFile(val_kmph,val_flag,tempSwitchChecked,formatDaySwitchChecked,stateBarSwitchChecked,notfSwirchChecked );
        goTohomeScreen();
    }

    private void getValues() {
        val_kmph = kmph_tv.getText().toString();
        val_flag = flagCheck;
        tempSwitchChecked = Temp_switch.isChecked();
        formatDaySwitchChecked = FormatDaySwitch.isChecked();
        stateBarSwitchChecked = StateBarSwitch.isChecked();
        notfSwirchChecked = Notf_swirch.isChecked();

    }

    private void saveValuesinSherdFile(String val_kmph, boolean val_flag, boolean tempSwitchChecked, boolean formatDaySwitchChecked, boolean stateBarSwitchChecked, boolean notfSwirchChecked) {
        SharedPreferences.Editor editorPrefrances = preferences.edit();
        editorPrefrances.putString("kmph",val_kmph);
        editorPrefrances.putBoolean("flag",val_flag);
        editorPrefrances.putBoolean("temp",tempSwitchChecked);
        editorPrefrances.putBoolean("state",stateBarSwitchChecked);
        editorPrefrances.putBoolean("notification",notfSwirchChecked);
        editorPrefrances.putBoolean("formatDate", formatDaySwitchChecked);
        editorPrefrances.apply();

    }

    private void goTohomeScreen() {
        Intent intent=new Intent(this,homeScreen.class);
        startActivity(intent);
        finish();
    }


}
