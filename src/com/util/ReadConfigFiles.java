package com.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by xiaoliang on 2018/10/1.
 */
public class ReadConfigFiles {

    //返回属性值，参数为属性名
    public String acquireAttributes(String key){

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
