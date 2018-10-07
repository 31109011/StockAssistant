package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class HaramiJudgement extends IJudge{

    static int num=2;

    public HaramiJudgement(KGraph sb) {
        super(sb, num);
    }

    //1.秉承前期走势，第1根K线为一个长长的实体，它将第2天的小实体及其上下影线完全包容起来。
    //2.在孕线形态中，两根K线的实体颜色应该互不相同，但这一点不是一项必要条件。
    //  其中，第2根K线实体的颜色并不重要。
    //3.孕线形态中，两个实体的相对大小是至关重要的，上下影线的大小，无关紧要。
    //4.孕线形态中，第2天的K线实体越小，整个形态的反转力量就越大，对短期股价产生较大影响。
    //5.十字孕线，即第2天的K线为十字线。这类形态出现在市场顶部或底部时，反转意愿更为强烈。
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);//当前
        boolean iscrss=isCross(c1);//第二根是否为十字线
        //两根颜色是否相反
        boolean isw0=isWrite(c0);
        boolean isw1=isWrite(c1);
        boolean bt=(isw0==true)&&(isw1==true);
        boolean bf=(isw0==false)&&(isw1==false);
        //如果不为十字线，则不能颜色相同
        if((iscrss==false)&&((bt)||(bf))){
            return false;
        }
        if(c1.open>max(c0.open,c0.close)){
            return false;
        }
        if(c1.close>max(c0.open,c0.close)){
            return false;
        }
        if(c1.open<min(c0.open,c0.close)){
            return false;
        }
        if(c1.close<min(c0.open,c0.close)){
            return false;
        }
        return true;
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":孕线形态";
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
