package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

public class BeltholdRiseJudgement extends IJudge{

    static int num=1;

    public BeltholdRiseJudgement(KGraph sb) {
        super(sb, num);
    }

    //看涨的捉腰带形态是一根坚挺的阳线，
    //其开盘价是当日最低价(或极短的下影线)，然后市场一路上涨。
    //这里判断为白色实体占据了最高至最低的三分之二(本没有该指标，这里为杜撰)
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c=lstcandle.get(0);
        if(isWrite(c)==false){
            return false;
        }
        if(c.open-c.lowest>=0.1*(c.higest-c.lowest)){
            return false;
        }
        if(c.close-c.open>2*(c.higest-c.lowest)/3){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":捉腰带线(看涨)";
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
