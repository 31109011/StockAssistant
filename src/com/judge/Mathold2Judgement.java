package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

public class Mathold2Judgement extends IJudge{

    static int num=4;

    public Mathold2Judgement(KGraph sb) {
        super(sb, num);
    }

    //中间为2黑色实体的铺垫形态-白-黑-黑-白
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);
        Candle c2=lstcandle.get(2);
        Candle c3=lstcandle.get(3);//当前
        if(isWrite(c0)==false){
            return false;
        }
        if((isWrite(c1)==true)||(isWrite(c2)==true)){
            return false;
        }
        //最后一根蜡烛线是白色的，
        if(isWrite(c3)==false){
            return false;
        }
        //向上跳空
        if(c1.close<c0.close){
            return false;
        }
        //最后一根蜡烛线是白色的，并且向上跳空，向上超过了上述最后一根黑色蜡烛的上影线
        //或者这根白色蜡烛线的收市价高于最后一根黑色蜡烛线的最高价，则构成买入信号
        if((c2.higest<c3.open)||(c3.close>c2.higest)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":铺垫形态2";
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
