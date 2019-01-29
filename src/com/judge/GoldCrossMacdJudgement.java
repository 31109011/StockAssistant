package com.judge;

import com.bean.Candle;
import com.bean.KGraph;
import com.technicalAnalysis.MACD;
import com.util.ConfigFile;

import java.util.ArrayList;

public class GoldCrossMacdJudgement extends IJudge {

    //用来获取所有数据
    static int num=0;

    public GoldCrossMacdJudgement(KGraph sb) {
        super(sb, num);
        n1=ConfigFile.getInstance().getCfb().macdn1;
        n2=ConfigFile.getInstance().getCfb().macdn2;
        n3=ConfigFile.getInstance().getCfb().macdn3;
    }

    //从配置文件中获取慢线n1和快线参数n2
    private int n1;
    private int n2;
    private int n3;

    @Override
    public boolean hasFeature() {
        ArrayList<Double> prices=new ArrayList<>();
        for(Candle c:lstcandle){
            prices.add(c.close);
        }
        MACD expma=new MACD(prices,n1,n2,n3);
        ArrayList<Double>dif=expma.getDif();
        ArrayList<Double>dea=expma.getDea();
        int length=lstcandle.size();
        if(length==0){
            return false;
        }
        int length1=length-ConfigFile.getInstance().getCfb().nperiod;
        if(length1<=2){
            return false;
        }
        if((dif.get(length1-1)>=dea.get(length1-1))&&
                (dif.get(length1-2)<=dea.get(length1-2))){
            return true;
        }
        return false;
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+"MACD金叉";
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
