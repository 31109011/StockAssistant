package com.judge;

import com.bean.Candle;
import com.bean.KGraph;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by xiaoliang on 2018/10/1.
 */
//用来判断蜡烛图的接口
public abstract class IJudge {
    //sb为判断特征时候用到的单根蜡烛，
    //num为判断特征时候用到的蜡烛根数
    public IJudge(KGraph sb,int num) {
        initCandle(sb,num);
        this.stockCode=sb.getSb().getCode();
        this.stockName=sb.getSb().getName();
        System.out.println("正在处理"+sb.getSb().getCode());
    }
    protected String stockCode;
    protected String stockName;
    //由于从网上下载的数据中除4价格外，还包含了总手，金额等，这里一并读取
    List<Candle> lstcandle=new ArrayList<Candle>();
    //判断该种蜡烛图是否符合特征
    public abstract boolean hasFeature();
    //蜡烛形状名称
    public abstract String judgeName();
    //建议
    public abstract String suggestion();
    //加强信号
    public abstract String strengthenSignals();

    //通过字符串进行拆分，生成单根蜡烛
    private Candle createCandle(String s){
        Candle c=new Candle();
        if(s==null){return c;}
        String[]p=s.split(",");
        c.date=p[0];
        c.open=Double.parseDouble(p[1]);
        c.close=Double.parseDouble(p[2]);
        c.higest=Double.parseDouble(p[3]);
        c.lowest=Double.parseDouble(p[4]);
        c.volume=Double.parseDouble(p[5]);
        c.sumofMoney=Double.parseDouble(p[6]);
        return c;
    }

    //初始化蜡烛图中需要的几根蜡烛，写入lstcandle中
    private void initCandle(KGraph sb,int num){
        if(sb==null){
            return;
        }
        List<String> lst=sb.getSb().getData();
        int length=lst.size();
        if((length==0)||(length<7)){
            return;
        }
        switch (num){
            case 6:
                String res6=lst.get(length-6);
                lstcandle.add(createCandle(res6));
            case 5:
                String res5=lst.get(length-5);
                lstcandle.add(createCandle(res5));
            case 4:
                String res4=lst.get(length-4);
                lstcandle.add(createCandle(res4));
            case 3:
                String res3=lst.get(length-3);
                lstcandle.add(createCandle(res3));
            case 2:
                String res2=lst.get(length-2);
                lstcandle.add(createCandle(res2));
            case 1:
                String res=lst.get(length-1);
                lstcandle.add(createCandle(res));
            case 0://如果取0根蜡烛，则是获取所有蜡烛，这是为了计算MA，MACD等。
                for(int i=0;i<lst.size();i++){
                    lstcandle.add(createCandle(lst.get(i)));
                }
                break;
        }
    }

    protected boolean isWrite(Candle c){
        //真为红，假为绿
        if(c.open>c.close){
            return false;
        }else{
            return true;
        }
    }
    protected boolean isCross(Candle c){
        return abs(c.open-c.close)<0.05*(c.higest-c.lowest);
    }
}
