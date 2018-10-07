package com.bean;

import net.sf.json.JSONObject;
import org.apache.commons.lang.WordUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xiaoliang on 2018/10/1.
 */
public class KGraph {

    //蜡烛图的时间间隔类型，如时、分、日、周、月等，通过不同的字母来表示
    private String type;

    //从服务器中返回的JSON数据
    StockBean sb;

    //构造方法为时间间隔和股票基本信息，查询蜡烛图
    public KGraph(String type,StockBasicInfo sbi) {
        this.type = type;
        acquireStockBeanfromInternet(sbi);
    }
    //从互联网获取蜡烛图的数据
    private void acquireStockBeanfromInternet(StockBasicInfo sbi){
        InputStream in=null;
        ByteArrayOutputStream out=null;
        try {
            URL u=new URL("http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?" +
                    "token=4f1862fc3b5e77c150a2b985b12db0fd&rtntype=6&" +
                    "id="+sbi.getId()+sbi.getType()+"&type="+this.type+
                    "&authorityType=fa&cb=jsonp1537100920536");
            in=u.openStream();
            out=new ByteArrayOutputStream();

            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
            byte bytes[]=out.toByteArray( );
            String res="";
            res = new String(bytes,"utf-8");
            String jsonstring=res.substring(19,res.length()-1);
            JSONObject jo=JSONObject.fromObject(jsonstring.toLowerCase());
            this.sb=(StockBean)JSONObject.toBean(jo, StockBean.class);
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public StockBean getSb() {
        return sb;
    }
    public void setSb(StockBean sb) {
        this.sb = sb;
    }
}
