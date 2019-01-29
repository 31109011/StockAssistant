package com.technicalAnalysis;

import java.util.ArrayList;

public class EXPMA {

    private ArrayList<Double>prices;
    private int n1,n2;

    private ArrayList<Double>ma1;
    private ArrayList<Double>ma2;

    public EXPMA(ArrayList<Double> prices, int n1, int n2) {
        this.prices = prices;


        this.n1 = n1;
        this.n2 = n2;

        ma1=new ArrayList<>();
        ma2=new ArrayList<>();
        ma1=expma(prices,n1);
        if(n2!=0){
            ma2=expma(prices,n2);
        }
    }


    public ArrayList<Double> getMa1() {
        return ma1;
    }

    public ArrayList<Double> getMa2() {
        return ma2;
    }

    //计算n日的指数平均数
    public ArrayList<Double>expma(ArrayList<Double>prices,int n){
        ArrayList<Double>res=new ArrayList<>();
        if(prices.size()==0){
            return res;
        }
        double tmpema=prices.get(0);
        res.add(tmpema);
        for(int i=1;i<prices.size();i++){
            tmpema=prices.get(i)*2/(n+1)+tmpema*(n-1)/(n+1);
            res.add(tmpema);
        }
        return res;
    }
}
