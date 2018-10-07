package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

public class ThreecrowsJudgement extends IJudge{

    static int num=3;

    public ThreecrowsJudgement(KGraph sb) {
        super(sb, num);
    }

    //连续出现三根相对较长的黑色蜡烛线，他们的收市价接近各自最低点，
    //如果它处于高价格水平，或者他出现在一轮持续了很长时间的上冲行情之后，则构成顶部反转信号
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);
        Candle c2=lstcandle.get(2);//当前
        if((isWrite(c0)==true)||(isWrite(c1)==true)||(isWrite(c2)==true)){
            return false;
        }
        if((c0.close-c0.lowest<0.1*(c0.higest-c0.lowest))&&
        (c1.close-c1.lowest<0.1*(c1.higest-c1.lowest))&&
        (c2.close-c2.lowest<0.1*(c2.higest-c2.lowest))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":三只乌鸦";
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
