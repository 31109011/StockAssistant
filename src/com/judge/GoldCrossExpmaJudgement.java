package com.judge;

import com.bean.Candle;
import com.bean.KGraph;
import com.util.ConfigFile;
import com.technicalAnalysis.EXPMA;

import java.util.ArrayList;

//该类表示通过expma来判断是否形成expma的金叉
public class GoldCrossExpmaJudgement extends IJudge{

    //用来获取所有数据
    static int num=0;

    //从配置文件中获取慢线n1和快线参数n2
    private int n1;
    private int n2;

    private void readConfig(){
        n1= ConfigFile.getInstance().getCfb().expman1;
        n2= ConfigFile.getInstance().getCfb().expman2;
    }

    public GoldCrossExpmaJudgement(KGraph sb) {
        super(sb, num);
        readConfig();
    }

    @Override
    public boolean hasFeature() {
        ArrayList<Double>prices=new ArrayList<>();
        for(Candle c:lstcandle){
            prices.add(c.close);
        }
        EXPMA expma=new EXPMA(prices,n1,n2);
        ArrayList<Double>expma1=expma.getMa1();
        ArrayList<Double>expma2=expma.getMa2();
        int length=lstcandle.size();
        if(length==0){
            return false;
        }
        int length1=length-ConfigFile.getInstance().getCfb().nperiod;
        if(length1<=2){
            return false;
        }
        if((expma1.get(length1-1)>=expma2.get(length1-1))&&
                (expma1.get(length1-2)<=expma2.get(length1-2))){
            return true;
        }
        return false;
    }

    @Override
    public String judgeName() {
        return stockCode+stockName+"EXPMA金叉";
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
