package com.example.a02.ui;

public class RecyclerItem {
    private String gns;
    private String content;
    private String gne;
    private String period;
    //private int check;

    public RecyclerItem(String content, String gns, String gne, String period){
        this.content = content;
        this.gns = gns;
        this.gne = gne;
        this.period = period;
        //this.check = check;
    }

    public String getContent(){ return this.content;}
    public String getPeriod(){ return this.period;}
    public String getGns(){ return this.gns;}
    public String getGne(){ return this.gne;}
    //public int getCheck(){return this.check;}

}