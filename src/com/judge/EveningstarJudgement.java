package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

//黄昏星形态
public class EveningstarJudgement extends IJudge{

    static int num=3;

    public EveningstarJudgement(KGraph sb) {
        super(sb, num);
    }

    //1.由三根蜡烛线组成，第一根是高高的白色实体；
    //2.第二根是一个小实体“即可以是白色，也可以为黑色），并且它向上跳空，形成一根星线；
    //3.第三根是黑色的蜡烛线，其收市价明显地向下穿入第一根白色实体的内部。
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
        if(isWrite(c2)==true){
            return false;
        }
        if(abs(c0.open-c0.close)-abs(c1.open-c1.close)<0){
            return false;
        }
        if(c0.close>min(c1.open,c1.close)){
            return false;
        }
        if((c2.close>min(c0.open,c0.close))&&(c2.close<max(c0.open,c0.close))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":黄昏星形态";
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
