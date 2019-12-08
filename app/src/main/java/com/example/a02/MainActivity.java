package com.example.a02;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CalendarDb calendarDb;

    // 임시로 데이터를 받을 변수들
    String tmp_content;
    int tmp_gns;
    int tmp_gne;
    String tmp_sound;
    String tmp_picture;
    int tmp_period;


    // 현재 날짜를 계산하여 리턴
    public String currDate()
    {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDate.format(mDate);

        return date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //mv.addDecorator(new SundayDecorator());
        //mv.addDecorator(new OneDayDecorator());



//        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        calendarDb = new CalendarDb(this, "mydb");
//        arrayList = new ArrayList<>();
//        mainAdapter = new MainAdapter(arrayList);

//        recyclerView.setAdapter(mainAdapter);


//        String date = currDate();
//        // db에 있는 오늘 날짜의 데이터 가져오기
//        ArrayList dbData = calendarDb.selectALLTABLE(date);
//
//        // mainData 형식으로 변환하여 arrayList에 추가
//        int i = 0;
//        while(i*6 < dbData.size() )
//        {
//            tmp_content = (String)dbData.get(1 + 6*i);
//            tmp_gns = (int)dbData.get(2 + 6*i);
//            tmp_gne = (int)dbData.get(3 + 6*i);
//            tmp_sound = (String)dbData.get(4 + 6*i);
//            tmp_picture = (String)dbData.get(5 + 6*i);
//            tmp_period = (int)dbData.get(6 + 6*i);
//
//            MainData mainData = new MainData(tmp_content, tmp_gns, tmp_gne, tmp_sound, tmp_picture, tmp_period);
//            arrayList.add(mainData);
//        }

//        Button btn_add = (Button)findViewById(R.id.btn);
//        btn_add.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//              MainData test = new MainData("test", 1, 1, "s", "p", 1);
//              arrayList.add(test);
//                mainAdapter.notifyDataSetChanged();
//            }
//        });
//
//        mainAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        calendarDb.closeDB();
    }
}
//class SundayDecorator implements DayViewDecorator{
//    private final Calendar calendar = Calendar.getInstance();
//
//    public SundayDecorator(){
//
//    }
//    @Override
//    public boolean shouldDecorate(CalendarDay day){
//
//        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//        return weekDay == Calendar.SUNDAY;
//    }
//    @Override
//    public void decorate(DayViewFacade view){
//        view.addSpan(new ForegroundColorSpan(Color.RED));
//    }
//}
//class OneDayDecorator implements DayViewDecorator{
//    private CalendarDay date;
//
//    OneDayDecorator(){
//        date = CalendarDay.today();
//    }
//    @Override
//    public boolean shouldDecorate(CalendarDay day){
//        return date != null && day.equals(date);
//    }
//    @Override
//    public void decorate(DayViewFacade view){
//        view.addSpan(new StyleSpan(Typeface.BOLD));
//        view.addSpan(new RelativeSizeSpan(1.4f));
//        view.addSpan(new ForegroundColorSpan(Color.GREEN));
//    }
//
//
//}