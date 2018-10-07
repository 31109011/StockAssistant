package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.abs;

public class CounterattackJudgement extends IJudge{

    static int num=2;

    public CounterattackJudgement(KGraph sb) {
        super(sb, num);
    }
    //当两个颜色相反的蜡烛线具有相同的收市价时，就形成了一个反击线/约会线形态
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);//当前
        if(((isWrite(c0)==true)&&(isWrite(c1)==true)||
                ((isWrite(c0)==false)&&(isWrite(c1)==false)))){
            return false;
        }
        if(abs(c0.close-c1.close)<0.05*(abs(c0.open-c1.open))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":反击线/约会线";
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
