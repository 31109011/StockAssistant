package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

public class PiercingJudgement extends IJudge{

    static int num=2;

    public PiercingJudgement(KGraph sb) {
        super(sb,num);
    }

    //1.市场处于下降趋势，第一天是一根大阴线
    //2.第二天是一根大阳线，它的开盘价低于第一天的最低价
    //3.第二天的收盘价应该高于第一天大阴线实体的中点
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);//当前
        if(isWrite(c0)==true){
            return false;
        }
        if(isWrite(c1)==false){
            return false;
        }
        if((c1.open<c0.lowest)&&(c1.close>0.5*(c0.open+c0.close))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":刺透形态(斩回线形态)";
    }

    @Override
    public String suggestion() {
        return null;
    }

    @Override
    public String strengthenSignals() {
        return null;
    }
}
