package com.example.a02.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.a02.R;
import com.example.a02.ui.LongPlanActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String today;
    View root;

    Button btn1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        MaterialCalendarView mv = (MaterialCalendarView)root.findViewById(R.id.calendarView);

        mv.setDateSelected(CalendarDay.today(),true);
        System.out.println(mv.getCurrentDate());
        mv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String s1 = widget.getSelectedDate().toString();
                System.out.println(s1);
                today = formatString(s1);
            }
        });


        btn1 = (Button)root.findViewById(R.id.btnLong);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), LongPlanActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
    private String formatString(String s1){
        //CalendarDay{yyyy-mm-dd}
        String s2 = "";
        if(s1.matches("^CalendarDay\\{\\d{4}-\\d{2}-\\d{2}\\}$")){
            s2 = s1.substring(12,22);
        }else if(s1.matches("^CalendarDay\\{\\d{4}-\\d{2}-\\d{1}\\}$")){
            s2 = s1.substring(12,20)+"0"+s1.substring(20,21);
        }else if(s1.matches("^CalendarDay\\{\\d{4}-\\d{1}-\\d{2}\\}$")){
            s2 = s1.substring(12,17)+"0"+s1.substring(17,21);
        }else if(s1.matches("^CalendarDay\\{\\d{4}-\\d{1}-\\d{1}\\}$")){
            s2 = s1.substring(12,17)+"0"+s1.substring(17,19)+"0"+s1.substring(19,20);
        }else{
            System.out.println("알맞은 형식이 아닙니다.");
        }
        return s2;
    }
}