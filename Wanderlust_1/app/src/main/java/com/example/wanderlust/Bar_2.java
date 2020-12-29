package com.example.wanderlust;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanderlust.activity.InsertAttendanceInfoActivity;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.Miui9Calendar;
import com.necer.enumeration.MultipleNumModel;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.listener.OnCalendarMultipleChangedListener;
import com.necer.painter.InnerPainter;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Bar_2 extends Fragment implements View.OnClickListener {

    Miui9Calendar myCalendar;
    RecyclerView recyclerView;
    ImageView image;
    TextView date;
    ImageView today;
    ImageView back;

    private boolean isResumeFirst = false;

    private LocalDate selectedDate;
    private String mDate;//当前日期
    private Button btnAddItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_2, container, false);
        myCalendar = view.findViewById(R.id.myCalendar);
        image = view.findViewById(R.id.img);
        recyclerView = view.findViewById(R.id.recyclerView);
        date = view.findViewById(R.id.item_date);
        today = view.findViewById(R.id.bar_today);
        btnAddItem = view.findViewById(R.id.bar_2_additem);

        btnAddItem.setOnClickListener(this);
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCalendar.toToday();
            }
        });

        selectedDate = myCalendar.getAllSelectDateList().get(0);
        mDate = localDate2DateString(selectedDate);
        date.setText(mDate);
        myCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate) {
                int mothOrDay = localDate.getDayOfMonth();
                int years = localDate.getYear();
                int months = localDate.getMonthOfYear();
                mDate = years + "-" + months + "-" + mothOrDay;
                date.setText(mDate);
                Log.d("Dong", "--->>>>>>>>>" + years + "-" + months +"-"+mothOrDay);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        return view;
    }

    public String localDate2DateString(LocalDate localDate){
        String year,month,day;
        year=localDate.getValue(0)+"";
        month=localDate.getValue(1)+"";
        day=localDate.getValue(2)+"";

        return year+"-"+month+"-"+day;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isResumeFirst){
                image.setVisibility(View.VISIBLE);
        }
        isResumeFirst = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bar_2_additem:
                startActivity(new Intent(getActivity(), InsertAttendanceInfoActivity.class)
                        .putExtra("currentDay",mDate));
                break;
        }
    }
}
