import com.bean.KGraph;
import com.bean.StockBasicInfo;
import com.dao.IBaseDao;
import com.impl.StockBasicInfoDao;
import com.judge.JudgmentManager;
import com.util.ConfigFile;
import com.util.Logger4j;

import java.util.List;


public class Test {

	public static void main(String[] args) {

		//读取配置文件中蜡烛图时间间隔
		String timeInterval= ConfigFile.getInstance().getCfb().candleStickInterval;
		//从数据库中读取所有股票代码和股票名称
		IBaseDao<StockBasicInfo> sbi=new StockBasicInfoDao();
		List<StockBasicInfo> lstsbi=sbi.getObjects();
		//以下代码为测试单个的，仅测试用
//		StockBasicInfo sbi=new StockBasicInfo();
//		sbi.setId("000001");
//		sbi.setName("123");
//		sbi.setType("2");
//		KGraph kg=new KGraph(timeInterval,sbi);
//		JudgmentManager j=new JudgmentManager();
//		j.initJudger(kg);
//		String ss=j.acquireResult();
//		System.out.println(ss);
		//对所有股票进行遍历，从中找出其中符合《日本蜡烛图技术》这本书中的蜡烛的股票
		//或者找出其他技术指标等的股票
		long startTime = System.currentTimeMillis();
		for(StockBasicInfo s:lstsbi){
			KGraph kg=new KGraph(timeInterval,s);
			JudgmentManager j=new JudgmentManager();
			j.initJudger(kg);
			String ss=j.acquireResult();
			if(!ss.equals("")){
				Logger4j.log.info(ss);
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("本次执行共耗时"+String.valueOf(endTime-startTime)+"毫秒");
	}
}
