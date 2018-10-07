package com.judge;

import com.bean.KGraph;

import java.util.ArrayList;
import java.util.List;

//所有判断方法的管理类，由此进行判断方法的遍历
public class JudgmentManager {
    List<IJudge> lstij=new ArrayList<IJudge>();
    public void initJudger(KGraph kg){
//        lstij.add(new HammerJudgement(kg));
//        lstij.add(new EngulfingJudgement(kg));
//        lstij.add(new DarkcloudcoverJudgement(kg));
//        lstij.add(new PiercingJudgement(kg));
//        lstij.add(new MorningstarJudgement(kg));
//        lstij.add(new EveningstarJudgement(kg));
//        lstij.add(new ShootingstarJudgement(kg));
//        lstij.add(new HaramiJudgement(kg));
//        lstij.add(new BeltholdRiseJudgement(kg));
//        lstij.add(new BeltholddeclineJudgement(kg));
//        lstij.add(new UpsidegaptwoCrowsJudgement(kg));
//        lstij.add(new Mathold2Judgement(kg));
//        lstij.add(new Mathold3Judgement(kg));
//        lstij.add(new Mathold4Judgement(kg));
//        lstij.add(new ThreecrowsJudgement(kg));
        lstij.add(new CounterattackJudgement(kg));
    }
    //用String来表示判断的所有蜡烛图所具有的特征的结果
    //表示结果为"股票代码股票名称：XXX线"组成的字符串
    public String acquireResult(){
        String res="";
        for(IJudge ij:lstij){
            if(ij.hasFeature()==true){
                res+=ij.judgeName();
            }
        }
        return res;
    }
}
