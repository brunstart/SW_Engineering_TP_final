package com.example.a02.ui.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a02.CalendarDb;
import com.example.a02.MainActivity;
import com.example.a02.MainAdapter;
import com.example.a02.MainData;
import com.example.a02.R;
import com.example.a02.ui.Progress;
import com.example.a02.ui.home.HomeFragment;
import com.github.mikephil.charting.charts.PieChart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ArrayList<MainData> arrayList = null;
    private ArrayList DbArr = null;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CalendarDb CDB;

    // 임시로 데이터를 받을 변수들
    String tmp_content;
    String tmp_gns;
    String tmp_gne;
    String tmp_sound;
    String tmp_picture;
    String tmp_period;

    int current_count = 0;
    int total_count = 0;

    private String today = HomeFragment.today;
    private String DB = "CalendarDB";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        arrayList = new ArrayList<MainData>();

        recyclerView.setLayoutManager(linearLayoutManager);
        CDB = new CalendarDb(getContext(), DB);

       // CDB.InsertTABLE(today,"test8",4,4,"http://m.naver.com","http://google.com",2);
        //CDB.InsertTABLE(today,"test9",2,4,"http://m.naver.com","http://google.com",0);
        //CDB.InsertTABLE(today,"test10",1,4,"http://m.naver.com","http://google.com",0);
       // CDB.InsertTABLE(today,"test11",5,5,"http://m.naver.com","http://google.com",1);
        //CDB.InsertTABLE(today,"가나다라마바사아자차카타파하",1,1,"","",0);

        DbArr = CDB.selectALLTABLE(today);
        System.out.println(DbArr.size());

        //arrayList.add(new MainData("내용","시작","끝","소리","사진","기간"));
    for (int i = 0; i < DbArr.size(); i = i + 6) {

        tmp_content = DbArr.get(i).toString();
        tmp_gns = DbArr.get(i + 1).toString();
        tmp_gne = DbArr.get(i + 2).toString();
        tmp_sound = DbArr.get(i + 3).toString();
        tmp_picture = DbArr.get(i + 4).toString();
        tmp_period = DbArr.get(i + 5).toString();

        if(tmp_gns.equals(tmp_gne)){
            current_count++;
        }
        total_count++;

        arrayList.add(new MainData(tmp_content, tmp_gns, tmp_gne, tmp_sound, tmp_picture, tmp_period));
    }

        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setAdapter(mainAdapter);



        new Progress((PieChart)view.findViewById(R.id.piechart_d),current_count,total_count);


        CDB.closeDB();
        /*
        Button btn_add = (Button)view.findViewById(R.id.btn);

        btn_add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                MainData test = new MainData("linkTest", "1", "1", "http://m.naver.com", "http://google.com", "1");
                arrayList.add(test);
                mainAdapter.notifyDataSetChanged();

            }
        });
*/
        return view;
    }


}