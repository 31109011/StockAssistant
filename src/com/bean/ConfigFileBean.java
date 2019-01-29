package com.bean;

/**
 * created by xiaoliang
 * 2019/1/29 20:06
 */
public class ConfigFileBean {
    //蜡烛图的时间间隔
    //分钟：r /日：k /周：wk /月：mk /5分钟：m5k /15分钟：m15k /30分钟：m30k /60分钟：m60k
    public String candleStickInterval;
    //expma参数 n1为慢线 n2为快线
    public int expman1;
    public int expman2;
    //macd参数 n1为dif慢线 n2为dif快线 n3为dif的expma参数
    public int macdn1;
    public int macdn2;
    public int macdn3;
    //查看n个时间周期前的expma金叉和macd金叉使用的参数,当前为0，前一为1
    public int nperiod=0;
}
