package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.max;
import static java.lang.Math.min;

//由一根蜡烛进行判断
//吞没形态(抱线形态)
public class EngulfingJudgement extends IJudge{

    static int num=2;

    public EngulfingJudgement(KGraph sb) {
        super(sb,num);
    }


    //1.在吞没形态之前,市场必须处在清晰可辨的上升趋势或下降趋势中,哪伯这个趋势只是短期的。
    //2.吞没形态必须由2条蜡烛线组成。
    //  其中第二根蜡烛线的实体必须覆盖第一根蜡烛线的实体（但是不一定需要吞没前者的上下影线）。
    //3.吞没形态的第二个实体必须与第一个实体的颜色相反。
    //  这一条标准有例外的情况,条件是,第一条蜡烛线的实体的必须非常小
    //  小得几乎构成了一根十字线（或者它就是一根十字线）。
    //  如此一来,如果在长期的下降趋势之后,一个小小的白色实体为一个巨大的白色实体所吞没,
    //  那么也可能构成了底部反转形态。反之,在上升趋势中,
    //  如果一个小小的黑色实体为一个巨大的黑色实体所吞没,那么也可能构成了顶部反转形态。
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);//当前
        //1.结合图形人工判断
        //2.先判断c1是否为十字线
        boolean isCross1=isCross(c1);
        //判断颜色
        boolean c0bw=isWrite(c0);
        boolean c1bw=isWrite(c1);
        if(isCross1==false){
            //如果不是，需要颜色相反的判断
            if(((c0bw==true)&&(c1bw==true))||((c0bw==false)&&(c1bw==false))){
                return false;
            }
        }
        //其次判断第二根蜡烛线的实体必须覆盖第一根蜡烛线的实体
        boolean c0o=(c0.open<max(c1.open,c1.close))&&(c0.open>min(c1.open,c1.close));
        boolean c0c=(c0.close<max(c1.open,c1.close))&&(c0.close>min(c1.open,c1.close));
        if((c0o==true)&&(c0c==true)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+":吞没形态(抱线形态)";
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
