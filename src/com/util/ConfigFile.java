package com.util;

import com.bean.ConfigFileBean;

import java.io.*;
import java.util.Properties;

/**
 * created by xiaoliang
 * 2019/1/29 20:13
 */
public class ConfigFile {

    private static ConfigFile instance=new ConfigFile();
    private ConfigFile(){
        cfb=new ConfigFileBean();
        cfb.candleStickInterval=acquireAttributes("candleStickInterval");
        cfb.expman1=Integer.valueOf(acquireAttributes("expman1"));
        cfb.expman2=Integer.valueOf(acquireAttributes("expman2"));
        cfb.macdn1=Integer.valueOf(acquireAttributes("macdn1"));
        cfb.macdn2=Integer.valueOf(acquireAttributes("macdn2"));
        cfb.macdn3=Integer.valueOf(acquireAttributes("macdn3"));
        cfb.nperiod=Integer.valueOf(acquireAttributes("nperiod"));
    }
    public static ConfigFile getInstance(){
        return instance;
    }

    public ConfigFileBean getCfb() {
        return cfb;
    }

    private ConfigFileBean cfb;

    //返回属性值，参数为属性名
    static private String acquireAttributes(String key){

        File directory = new File("");
        String file = null;
        try {
            file = directory.getCanonicalPath()+"/config.properties";
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
            return "";
        }
        return p.getProperty(key);
    }

}
