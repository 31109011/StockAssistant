package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class ShootingstarJudgement extends IJudge {

    static int num=1;

    public ShootingstarJudgement(KGraph sb) {
        super(sb, num);
    }

    //1.形态为上影线长，至少是实体的2倍；下影线没有或者很短。
    //2.流星线和倒锤子线一样，对实体颜色的要求并不重要。
    //  流星线实体为绿色，见顶信号更明确；倒锤子线实体为红色，对价格做底的预示更强烈。
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c=lstcandle.get(0);
        if(2*abs(c.open-c.close)>=(c.higest-max(c.open,c.close))){
            return false;
        }
        if((min(c.open,c.close)==c.lowest)||
                (min(c.open,c.close)-c.lowest)<(0.1*(c.higest-c.close))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":流星线/倒锤子线";
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
