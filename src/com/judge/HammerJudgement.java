package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by xiaoliang on 2018/10/1.
 */
//由一根蜡烛进行判断
//锤子线/上吊线
public class HammerJudgement extends IJudge{

    static int num=1;

    public HammerJudgement(KGraph sb) {
        super(sb,num);
    }

    //1.实体位于整个价格区间的上端或底部(人工判断)
    //2.下影线的长度至少达到实体高度的2倍
    //3.在这类蜡烛线中，应当没有上影线，即使有上影线，其长度也是极短的
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c=lstcandle.get(0);
        //实体位于整个价格区间的上端或底部。
        //即使有上影线，其长度也是极短的(这里设置上影线为最高减去最低的十分之一)。
        boolean b1=(c.open==c.higest)||
                (c.higest-max(c.open,c.close))<0.1*(c.higest-c.close);
        //下影线的长度至少达到实体高度的2倍。
        boolean b2=((min(c.open,c.close)-c.lowest)>2*abs(c.open-c.close));
        return b1&&b2;
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":锤子线/上吊线";
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
