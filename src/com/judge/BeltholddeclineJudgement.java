package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

public class BeltholddeclineJudgement extends IJudge{

    static int num=1;

    public BeltholddeclineJudgement(KGraph sb) {
        super(sb, num);
    }

    //看跌捉腰带形态是一根长长的阴线，
    //其开盘价是当日最高价(或有极短的上影线)
    //这里判断为黑色实体占据了最高至最低的三分之二(本没有该指标，这里为杜撰)
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c=lstcandle.get(0);
        if(isWrite(c)==true){
            return false;
        }
        if(c.higest-c.open>=0.1*(c.higest-c.lowest)){
            return false;
        }
        if(c.open-c.close>2*(c.higest-c.lowest)/3){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":捉腰带线(看跌)";
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
