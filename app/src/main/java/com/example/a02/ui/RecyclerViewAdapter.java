package com.example.a02.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a02.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    ArrayList<RecyclerItem> mDataset;
    int checked = 0;
    private static int count = 0;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox check;
        public TextView gns;
        public TextView gne;
        public TextView period;
        public TextView content;
        public MyViewHolder(View view){
            super(view);
            check = (CheckBox)view.findViewById(R.id.check);
            gns = (TextView) view.findViewById(R.id.gns);
            gne = (TextView) view.findViewById(R.id.gne);
            period = (TextView) view.findViewById(R.id.period);
            content = (TextView) view.findViewById(R.id.content);
        }
    }

    public RecyclerViewAdapter(ArrayList<RecyclerItem> myDataset){
        mDataset = myDataset;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){

        String gns = mDataset.get(position).getGns();
        String gne = mDataset.get(position).getGne();

        if(gns.equals(gne)){
            checked = 1;
            count++;
            System.out.println("count++ "+count);
        }else checked = 0;

        holder.content.setText(mDataset.get(position).getContent());
        holder.gns.setText(gns);
        holder.gne.setText(gne);
        holder.period.setText(mDataset.get(position).getPeriod());
        if(checked == 1) {
            holder.check.setChecked(true);

        }else {
            holder.check.setChecked(false);
        }
        holder.check.setEnabled(false);
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    ((CheckBox)v).setChecked(false);
                }else{
                    ((CheckBox)v).setChecked(true);
                }
            }
        });

    }
    @Override
    public int getItemCount(){
        return mDataset.size();
    }
    public static int getCount(){
        return count;
    }
    public static void InitCount(){
        count = 0;
    }



}
