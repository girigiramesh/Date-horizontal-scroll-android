package com.datescrollhorizontal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Ramesh on 4/19/17.
 */

public class DateFragment extends Fragment {
    private Calendar startCalendar;
    private Calendar endCalendar;

    private RecyclerView mDaysRecyclerView;
    private Calendar mCalendar = Calendar.getInstance();
    private View mPriorMonths, mFutureMonths;
    private TextView mMonthAndYear;
    private final String[] monthArray = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        mDaysRecyclerView = (RecyclerView) rootView.findViewById(R.id.days_recycler_view);
        mPriorMonths = rootView.findViewById(R.id.prior_months);
        mFutureMonths = rootView.findViewById(R.id.future_months);
        mMonthAndYear = (TextView) rootView.findViewById(R.id.month_year);

        mMonthAndYear.setText(monthArray[mCalendar.get(Calendar.MONTH)] + " " + mCalendar.get(Calendar.YEAR));
        int[] daysOfMonth = new int[mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)];
        for (int i = 0; i < daysOfMonth.length; ++i) {
            daysOfMonth[i] = i + 1;
        }
        int selectedDay = mCalendar.get(Calendar.DAY_OF_MONTH) - 1;
        mCalendar.set(Calendar.DATE, 1);
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH) + 1;
        DayAdapter dayAdapter = new DayAdapter(getActivity(), daysOfMonth, dayOfWeek, selectedDay, year, month);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mDaysRecyclerView.setLayoutManager(mLayoutManager);
        mDaysRecyclerView.setAdapter(dayAdapter);
        mDaysRecyclerView.smoothScrollToPosition(selectedDay);

        mCalendar = Calendar.getInstance();
        setListeners();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_YEAR, 1);
    }

    private void setListeners() {
        mPriorMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, -1);
                mMonthAndYear.setText(monthArray[mCalendar.get(Calendar.MONTH)] + " " + mCalendar.get(Calendar.YEAR));
                int[] daysOfMonth = new int[mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)];
                for (int i = 0; i < daysOfMonth.length; ++i) {
                    daysOfMonth[i] = i + 1;
                }
                mCalendar.set(Calendar.DATE, 1);
                int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH) + 1;
                ((DayAdapter) mDaysRecyclerView.getAdapter()).notifyAdapter(daysOfMonth, dayOfWeek, year, month);
                mDaysRecyclerView.smoothScrollToPosition(0);
            }
        });
        mFutureMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, 1);
                mMonthAndYear.setText(monthArray[mCalendar.get(Calendar.MONTH)] + " " + mCalendar.get(Calendar.YEAR));
                int[] daysOfMonth = new int[mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)];
                for (int i = 0; i < daysOfMonth.length; ++i) {
                    daysOfMonth[i] = i + 1;
                }
                mCalendar.set(Calendar.DATE, 1);
                int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH) + 1;
                ((DayAdapter) mDaysRecyclerView.getAdapter()).notifyAdapter(daysOfMonth, dayOfWeek, year, month);
                mDaysRecyclerView.smoothScrollToPosition(0);
            }
        });
    }

    public void setCalendars(Calendar startCalendar, Calendar endCalendar) {
        this.startCalendar = startCalendar;
        this.endCalendar = endCalendar;
    }
}
