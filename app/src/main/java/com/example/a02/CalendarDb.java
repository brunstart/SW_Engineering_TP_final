package com.example.a02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class CalendarDb {

    private SQLiteDatabase db = null;

    public CalendarDb(Context context, String str){
        openDB(context, str);
    }

    public void openDB(Context context, String DB){
        try{
            db = openOrCreateDatabase(context.getDatabasePath(DB),null);

            System.out.println("데이터베이스 생성 성공");


        }catch (Exception ex){
            System.out.println("데이터베이스 생성 오류");
        }
    }

    public void closeDB(){
        try{
            db.close();
        }catch(Exception ex){

        }
    }

    public void makeTABLE(String name){
        try{
            String CREATE_SQL =
                    "create table "+formatTable(name)+
                            "(id integer PRIMARY KEY autoincrement,"+
                            "content text not null,"+
                            "gns integer,"+
                            "gne integer,"+
                            "sound text,"+
                            "picture text,"+
                            "period integer);";
            // TABLE : caYYYYMMDD , Schema : id(autoincrement), content(text), gns(int), gne(int), sound(text), picture(text), period(int)

            System.out.println(CREATE_SQL);

            db.execSQL(CREATE_SQL);

            System.out.println("테이블 생성 성공");
        }catch(Exception ex){
            System.out.println("테이블 생성 실패");
        }
    }
    public void InsertTABLE(String date, String content){
        try{
            makeTABLE(date);

            ContentValues RecordValues = new ContentValues();
            RecordValues.put("content",content);
            db.insert(formatTable(date),null, RecordValues);
            System.out.println("삽입 성공");
        }catch(Exception ex){
            System.out.println("삽입 실패");
        }
    }
    public void InsertTABLE(String date, String content, int gns, int gne, String sound, String picture){
        try{
            makeTABLE(date);

            ContentValues RecordValues = new ContentValues();
            RecordValues.put("content",content);
            RecordValues.put("gns",gns);
            RecordValues.put("gne",gne);
            RecordValues.put("sound",sound);
            RecordValues.put("picture",picture);

            db.insert(formatTable(date),null, RecordValues);

        }catch(Exception ex){

        }
    }
    public void InsertTABLE(String date, String content, int period){
        try{
            InsertTABLE(date,content, 0, 0,null,null,period);

        }catch(Exception ex){

        }
    }
    public void InsertTABLE(String date, String content, int gns, int gne, String sound, String picture, int period){
        try{
            String str = null;
            if(period > 0) {
                makeTABLE(date);

                str = getPeriod(date, period);

                ContentValues RecordValues = new ContentValues();
                RecordValues.put("content", content);
                RecordValues.put("gns", gns);
                RecordValues.put("gne", gne);
                RecordValues.put("sound", sound);
                RecordValues.put("picture", picture);
                RecordValues.put("period", period);

                db.insert(formatTable(date), null, RecordValues);

                InsertTABLE(str, content, gns, gne, sound, picture, period-1);
            }

        }catch(Exception ex){

        }
    }

    public void updateTABLE(String find, String date, String original , String modify){
        try{
            String UPDATE_SQL = "update "+formatTable(date)+" set "+find+" = '"+modify+"' where "+find+" = '"+original+"';";


            db.execSQL(UPDATE_SQL);

        }catch(Exception ex){

        }
    }
    public void deleteTABLE(String content, String date){
        try {
            String DELETE_SQL = "delete from " + formatTable(date) + " where content = " + content + ";";

            db.execSQL(DELETE_SQL);
        }
        catch(Exception ex){

        }
    }

    public ArrayList selectTable(String find, String date){
        ArrayList list = new ArrayList();
        String SELECT_SQL = "select "+find+" from "+formatTable(date);
        Cursor cursor = db.rawQuery(SELECT_SQL,null);

        while(cursor.moveToNext()){
            list.add(checkNull(cursor.getString(0)));
        }
        return list;
    }

    public ArrayList selectALLTABLE(String date){
        ArrayList list = new ArrayList();
        try{
            String SELECT_SQL = "select * from "+formatTable(date);
            System.out.println(formatTable(date));
            Cursor cursor = db.rawQuery(SELECT_SQL,null);
            while(cursor.moveToNext()){
                for(int i = 1; i < 7; i++) {
                    list.add(checkNull(cursor.getString(i)));
                }
            }

        }catch(Exception ex){

        }
        return list;
    }
    public ArrayList selectPeriodTable(String date){
        ArrayList list = new ArrayList();
        try{
            String SELECT_SQL = "select * from "+formatTable(date)+" where period > 1";
            Cursor cursor = db.rawQuery(SELECT_SQL,null);
            while(cursor.moveToNext()){
                for(int i = 1; i < 7; i++){
                    list.add(checkNull(cursor.getString(i)));
                }
            }
        }
        catch(Exception ex){

        }
        return list;
    }
    public String checkNull(String str){
        if(str == null) return "";
        else return str;
    }

    public String getPeriod(String date, int period){
        if(period > 0) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            String str = null;
            //YYYY-MM-DD
            if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                int y = Integer.parseInt(date.substring(0, 4));
                int m = Integer.parseInt(date.substring(5, 7));
                int d = Integer.parseInt(date.substring(8, 10));

                cal.set(y, m-1, d);

                cal.add(Calendar.DATE, 1);
                str = sdf.format(cal.getTime());

                return str;
            }
            //caYYYYMMDD
            else if(date.matches("^ca\\d{8}$")){
                int y = Integer.parseInt(date.substring(2,6));
                int m = Integer.parseInt(date.substring(6,8));
                int d = Integer.parseInt(date.substring(8,10));

                cal.set(y,m-1,d);

                cal.add(Calendar.DATE, 1);
                str = sdf.format(cal.getTime());

                return str;
            }
            else return "";
        }else return "";
    }

    public String formatTable(String str){
        if(str.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            System.out.println(str);
            String str1 = str.substring(0,4)+ str.substring(5,7)+str.substring(8,10);
            return "ca"+str1;
        }
        else
            return "";
    }
}
