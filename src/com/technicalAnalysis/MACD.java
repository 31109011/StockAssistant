package com.technicalAnalysis;

import java.util.ArrayList;

public class MACD {

    //通过prices,n1,n2,n3来初始化该MACD结构
    //prices为价格序列，n1为dif慢线参数，n2为dif快线参数，n3为平滑dif的参数
    private ArrayList<Double>prices;
    private int n1,n2,n3;

    public MACD(ArrayList<Double> prices, int n1, int n2, int n3) {
        this.prices = prices;


        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;

        dif=new ArrayList<>();
        dea=new ArrayList<>();
        macd=new ArrayList<>();
        caldif();
        caldea();
        calmacd();
    }

    private ArrayList<Double>dif;
    private ArrayList<Double>dea;
    private ArrayList<Double>macd;

    public ArrayList<Double> getDif() {
        return dif;
    }

    public ArrayList<Double> getDea() {
        return dea;
    }

    public ArrayList<Double> getMacd() {
        return macd;
    }

    //计算快周期为n1，慢周期为n2的dif
    private void caldif(){
        EXPMA exp=new EXPMA(prices,n1,n2);
        for(int i=0;i<exp.getMa1().size();i++){
            dif.add(exp.getMa1().get(i)-exp.getMa2().get(i));
        }
    }

    //根据dif计算dea
    private void caldea(){
        EXPMA exp=new EXPMA(dif,n3,0);
        dea=exp.getMa1();
    }

    //计算macd
    private void calmacd(){
        for(int i=0;i<dif.size();i++){
            macd.add((dif.get(i)-dea.get(i))*2);
        }
    }

}
