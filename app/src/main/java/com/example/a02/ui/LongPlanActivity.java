package com.example.a02.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a02.CalendarDb;
import com.example.a02.MainActivity;
import com.example.a02.R;
import com.example.a02.ui.home.HomeFragment;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class LongPlanActivity extends Activity {
    Button btnBack;

    RelativeLayout progressLayout;

    int current_count = 0;
    int total_count = 0;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    CalendarDb calendarDb = null;
    String today = HomeFragment.today;

    ArrayList<RecyclerItem> recyclerItemArr = null;
    ArrayList dbArr = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longplan);

        btnBack = (Button)findViewById(R.id.btnBack);
        progressLayout = (RelativeLayout)findViewById(R.id.progresslayout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerItemArr = new ArrayList<RecyclerItem>();

        calendarDb = new CalendarDb(this,"CalendarDB");
        dbArr = calendarDb.selectPeriodTable(today);

        recyclerItemArr.add(new RecyclerItem(new String("내용"),new String("시작"),new String("끝"),new String("기간")));

/*
        calendarDb.InsertTABLE(today,"test1",3,3,null,null,1);
        calendarDb.InsertTABLE(today,"test2",3,6,null,null,4);
        calendarDb.InsertTABLE(today,"test3",3,6,null,null,1);
        calendarDb.InsertTABLE(today,"test4",3,3,null,null,3);
        calendarDb.InsertTABLE(today,"test5",3,6,null,null,2);
        calendarDb.InsertTABLE(today,"test6",3,3,null,null,3);
        calendarDb.InsertTABLE(today,"test7",3,8,null,null,1);
*/

        for(int i = 0; i < dbArr.size(); i=i+6){
            String dcontent = dbArr.get(i).toString();
            String dgns = dbArr.get(i+1).toString();
            String dgne = dbArr.get(i+2).toString();
            String period = dbArr.get(i+5).toString();

            recyclerItemArr.add(new RecyclerItem(dcontent,dgns,dgne,period));

            if(dgns.equals(dgne)){
                current_count++;
            }
            total_count++;
        }

        new Progress((PieChart)findViewById(R.id.piechart),current_count,total_count);

        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(recyclerItemArr);
        recyclerView.setAdapter(adapter);

        System.out.println("count : "+RecyclerViewAdapter.getCount());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });
        calendarDb.closeDB();
    }
}
