package com.datescrollhorizontal;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by alex.syskin on 12/5/16.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayHolder> {
    private Context context;
    private int[] daysOfMonth;
    private Map<Integer, String> mapping;
    private String[] daysOfWeek = new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private int selectedDay, year, month;

    public DayAdapter(Context context, int[] daysOfMonth, int dayOfWeek, int selectedDay, int year, int month) {
        this.context = context;
        this.daysOfMonth = daysOfMonth;
        mapping = new HashMap<>();
        for (int i = 0; i < daysOfMonth.length; ++i) {
            mapping.put(daysOfMonth[i], daysOfWeek[(dayOfWeek - 1) % 7]);
            ++dayOfWeek;
        }
        this.selectedDay = selectedDay;
        this.year = year;
        this.month = month;
    }

    public void notifyAdapter(int[] daysOfMonth, int dayOfWeek, int year, int month) {
        this.daysOfMonth = daysOfMonth;
        mapping = new HashMap<>();
        for (int i = 0; i < daysOfMonth.length; ++i) {
            mapping.put(daysOfMonth[i], daysOfWeek[(dayOfWeek - 1) % 7]);
            ++dayOfWeek;
        }
        selectedDay = 0;
        this.year = year;
        this.month = month;
        notifyDataSetChanged();
    }

    @Override
    public DayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.row_day_adapter_item, parent, false);
        return new DayHolder(view);
    }

    @Override
    public void onBindViewHolder(DayHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth[position] + "");
        holder.dayOfWeek.setText(mapping.get(daysOfMonth[position]));
        if (selectedDay == position) {
            holder.dayOfMonth.setBackgroundResource(R.drawable.item_selection);
            holder.dayOfMonth.setTextColor(Color.parseColor("#51BA7B"));
        } else {
            holder.dayOfMonth.setBackground(null);
            holder.dayOfMonth.setTextColor(Color.parseColor("#51BA7B"));
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public long getCurrentTimestamp() {  // call this --- ((DayAdapter) mDaysRecyclerView.getAdapter()).getCurrentTimestamp()
        String date = year + "-" + month + "-" + daysOfMonth[selectedDay];
        Date date1 = new Date();
        try {
            date1 = new SimpleDateFormat("MM-d-yyyy", Locale.getDefault()).parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date1.getTime();
    }

    public class DayHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dayOfWeek;
        TextView dayOfMonth;

        public DayHolder(View item) {
            super(item);
            dayOfWeek = (TextView) item.findViewById(R.id.dayOfWeek);
            dayOfMonth = (TextView) item.findViewById(R.id.dayOfMonth);
            dayOfMonth.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            selectedDay = getLayoutPosition();
            String date = year + "-" + month + "-" + dayOfMonth.getText();
            notifyDataSetChanged();
        }
    }
}
