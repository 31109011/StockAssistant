package com.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.C3P0Utils;
 
/**
 * @author chmin<br>
 * @time 2016年1月23日 上午9:41:43<br>
 */
public class DaoHandle {
 
    /**
     * 更新和删除
     * @param sql
     * @param parameters
     * @return
     */
    public static int executeUpdate(String sql, Object[] parameters) {
        return execute(sql, parameters, 0);
    }
     
    /**
     * 添加
     * @param sql
     * @param parameters
     * @return
     */
    public static int insert(String sql, Object[] parameters) {
        return execute(sql, parameters, 1);
    }
     
    /**
     * 执行增删改
     * @param sql
     * @param parameters
     * @param type 0为删改，1为增加
     * @return
     */
    private static int execute(String sql, Object[] parameters, int type){
        Connection con = C3P0Utils.openConnection();
        int count = 0;
        if (con != null) {
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                for (int i = type + 1; i <= parameters.length + type; i++) {
                    ps.setObject(i, parameters[i - (1 + type)]);
                }
                count = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                C3P0Utils.closeAll(con, null, null);
            }
        }
        return count;
    }
 
    /**
     * 执行查询，并将值反射到bean
     * @param sql
     * @param parameters
     * @param clazz
     * @return
     */
    public static <T> List<T> select(String sql, Object[] parameters, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        Connection conn = C3P0Utils.openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            if(parameters != null){
                for (int i = 1; i <= parameters.length; i++) {
                    ps.setObject(i, parameters[i - 1]);
                }
            }
            // 执行查询方法
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            List<String> columnList = new ArrayList<String>();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                columnList.add(rsmd.getColumnName(i + 1));
            }
            // 循环遍历记录
            while (rs.next()) {
                // 创建封装记录的对象
                T obj = clazz.newInstance();
                // 遍历一个记录中的所有列
                for (int i = 0; i < columnList.size(); i++) {
                    // 获取列名
                    String column = columnList.get(i);
                    // 根据列名创建set方法
                    String setMethd = "set" + column.substring(0, 1).toUpperCase() + column.substring(1);
                    // 获取clazz中所有方法对应的Method对象
                    Method[] ms = clazz.getMethods();
                    // 循环遍历ms
                    for (int j = 0; j < ms.length; j++) {
                        // 获取每一个method对象
                        Method m = ms[j];
                        // 判断m中对应的方法名和数据库中列名创建的set方法名是否形同
                        if (m.getName().equals(setMethd)) {
                            // 反调set方法封装数据
                            m.invoke(obj, rs.getObject(column));// 获取rs中对应的值，封装到obj中
                            break; // 提高效率
                        }
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            C3P0Utils.closeAll(conn, ps, rs);
        }
        return list;
    }
}