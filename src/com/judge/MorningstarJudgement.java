package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

//启明星形态
public class MorningstarJudgement extends IJudge{

    static int num=3;

    public MorningstarJudgement(KGraph sb) {
        super(sb, num);
    }

    //1.在这个形态中，先是有一根长的黑色实体
    //  (这根黑色实体蜡烛线出现时，市场正处于下降趋势中。到此时为止，熊方还占着上风)-人工判断
    //2.其后是一根与其相对较小的实体(这就意味着卖方已经失去了将市场进一步压低的能量)
    //  并且在这两个实体之间形成了一个向下跳空，由这两条蜡烛线组成了基本的星线形态
    //3.接下来是一根白色实体，他十分明显的向上推进了前一天黑色实体的内部
    @Override
    public boolean hasFeature() {
        if(lstcandle.size()==0){
            return false;
        }
        Candle c0=lstcandle.get(0);
        Candle c1=lstcandle.get(1);
        Candle c2=lstcandle.get(2);//当前
        if(isWrite(c0)==true){
            return false;
        }
        if(isWrite(c2)==false){
            return false;
        }
        if(abs(c0.open-c0.close)-abs(c1.open-c1.close)<0){
            return false;
        }
        if(c0.close<max(c1.open,c1.close)){
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
        return stockCode+stockName+":启明星形态";
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
