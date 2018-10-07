package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

//向上跳空两只乌鸦
public class UpsidegaptwoCrowsJudgement extends IJudge{

    static int num=3;

    public UpsidegaptwoCrowsJudgement(KGraph sb) {
        super(sb, num);
    }

    //第一根是一根长长的白色蜡烛，第二根是一个向上跳空的黑色实体
    //第三根也是一个黑色实体，其开市价高于第二根蜡烛线的开市价，且收市价低于第二根蜡烛线的收市价
    //这是一个顶部反转信号
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);
        Candle c2=lstcandle.get(2);//当前
        if(isWrite(c0)==false){
            return false;
        }
        if((isWrite(c1)==true)||(isWrite(c2)==true)){
            return false;
        }
        //向上跳空
        if(c1.close<c0.close){
            return false;
        }
        //第三根也是一个黑色实体，其开市价高于第二根蜡烛线的开市价，且收市价低于第二根蜡烛线的收市价
        if((c2.open>c1.open)&&(c2.close<c1.close)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":向上跳空两只乌鸦";
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
