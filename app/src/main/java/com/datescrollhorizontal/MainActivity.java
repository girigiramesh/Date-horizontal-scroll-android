package com.datescrollhorizontal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DateFragment dateFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_schedule);
        dateFragment = new DateFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, dateFragment).commitAllowingStateLoss();
    }

    public void setCalendars(Calendar startCalendar, Calendar endCalendar) {
        if (dateFragment != null) {
            dateFragment.setCalendars(startCalendar, endCalendar);
        }
    }
}