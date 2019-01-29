package com.technicalAnalysis;

import java.util.ArrayList;

public class MA {

    private ArrayList<Double> prices;
    private int n1,n2,n3;

    public ArrayList<Double> getMa1() {
        return ma1;
    }

    public ArrayList<Double> getMa2() {
        return ma2;
    }

    public ArrayList<Double> getMa3() {
        return ma3;
    }

    private ArrayList<Double>ma1;
    private ArrayList<Double>ma2;
    private ArrayList<Double>ma3;

    public MA(ArrayList<Double> prices, int n1, int n2, int n3) {
        this.prices = prices;


        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;

        ma1=new ArrayList<>();
        ma2=new ArrayList<>();
        ma3=new ArrayList<>();

        ma1=calma(prices,n1);
        if(n2!=0){
            ma2=calma(prices,n2);
        }
        if(n3!=0){
            ma3=calma(prices,n3);
        }

    }

    private ArrayList<Double>calma(ArrayList<Double>prices, int n){
        ArrayList<Double>res=new ArrayList<>();
        //前n个数字计算出第一个均值
        Double sum=0.0;//和
        Double avg=0.0;//平均值
        if(prices.size()>n){
            for(int i=0;i<n;i++){
                sum+=prices.get(i);
            }
            avg=sum/n;
            res.add(avg);
            for(int i=n;i<prices.size();i++){
                avg=(avg*n-prices.get(i-n)+prices.get(i))/n;
                res.add(avg);
            }
        }
        return res;
    }

}
