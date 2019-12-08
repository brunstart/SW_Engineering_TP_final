package com.example.a02;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a02.ui.Progress;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;
    private Context c;
    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder customViewHolder, int i) {


        customViewHolder.itemView.setTag(i);

        customViewHolder.content.setText(arrayList.get(i).getContent());
        customViewHolder.gns.setText(new String(arrayList.get(i).getGns()+""));
        customViewHolder.gne.setText(new String(arrayList.get(i).getGne()+""));
        customViewHolder.period.setText(new String(arrayList.get(i).getPeriod()+""));

        final String pic = arrayList.get(i).getPicture();
        final String snd = arrayList.get(i).getSound();

        if(arrayList.get(i).getGns().equals(arrayList.get(i).getGne())){
            customViewHolder.checkBox.setChecked(true);
        }

        customViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int total_count = 0;
                int current_count = 0;
                for(int i = 0; i < arrayList.size(); i++) {

                    if( arrayList.get(i).getCheckBox().isChecked() ) {
                        current_count++;
                    }
                    total_count++;
                }
                new Progress((PieChart)view.findViewById(R.id.piechart_d),current_count,total_count);
            }
        });

        customViewHolder.content.setMovementMethod(new ScrollingMovementMethod());
        // 사진 버튼 -> 아이콘 추가후 수정 필요
        customViewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pic));
                c.startActivity(intent);
            }
        });

        // 소리 버튼 -> 아이콘 추가후 수정 필요
        customViewHolder.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(snd));
                c.startActivity(intent2);
            }
        });

        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                customViewHolder.checkBox.toggle();

            }
        });

        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public TextView content;
        public TextView gns;
        public TextView gne;
        public Button picture;
        public Button sound;
        public TextView period;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            this.content = (TextView) itemView.findViewById(R.id.item_content);
            this.gns = (TextView) itemView.findViewById(R.id.item_gns);
            this.gne = (TextView)itemView.findViewById(R.id.item_gne);
            this.picture = (Button) itemView.findViewById(R.id.button_picture);
            this.sound = (Button) itemView.findViewById(R.id.button_sound);
            this.period = (TextView)itemView.findViewById(R.id.item_period);

            c = itemView.getContext();
        }
    }
}
