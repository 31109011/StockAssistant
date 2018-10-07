package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

//乌云盖顶形态
public class DarkcloudcoverJudgement extends IJudge{

    static int num=2;

    public DarkcloudcoverJudgement(KGraph sb) {
        super(sb, num);
    }
    //1.第一个交易日是一根红色的K线实体
    //2.第二个交易日的开盘价超过了第一个交易日的最高价（也就是超过了第一个交易日的上影线顶端）
    //  但是收盘价却接近当日的最低价水平并且收盘价明显向下插入第一个交易日的K线实体内部
    //  如果第二个交易日的绿色K线实体向下插入第一个交易日的K线实体的程度越深
    //  那么该形态构成顶部反转的可能性就越大
    //3.按照实战经验来看，第二个交易日的K线实体收盘价必须向下插入第一个交易日K线实体的50%
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);//当前
        if(isWrite(c0)==false){
            return false;
        }
        if(isWrite(c1)==true){
            return false;
        }
        if((c1.open>c0.higest)&&(c1.close<0.5*(c0.open+c0.close))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":乌云盖顶(乌云线形态)";
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
