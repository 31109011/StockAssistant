package com.impl;

import java.util.List;

import com.bean.StockBasicInfo;
import com.dao.DaoHandle;
import com.dao.IBaseDao;

public class StockBasicInfoDao implements IBaseDao<StockBasicInfo> {
    /**
     * 方便使用dao
     */
    public static StockBasicInfoDao dao = new StockBasicInfoDao();

    /**
     * 保存实体
     */
    @Override
    public boolean add(StockBasicInfo t) {
        String sql = "insert into t_stock values (?,?,?)";
        Object[] parameters = new Object[]{t.getId(),t.getName(),t.getType()};
        return DaoHandle.insert(sql, parameters) == 1 ? true : false;
    }

    /**
     * 删除实体
     */
    @Override
    public boolean remove(StockBasicInfo t) {
        return remove(t.getId());
    }

    /**
     * 删除实体
     */
    public boolean remove(String id) {
        String sql = "delete from t_stock where id = (select id from t_stock where id = ?)";
        Object[] parameters = new Object[]{id};
        DaoHandle.executeUpdate(sql, parameters);
        sql = "delete from t_stock where id = ?";
        return DaoHandle.executeUpdate(sql, parameters) == 1 ? true : false;
    }

    /**
     * 更新
     */
    @Override
    public boolean update(StockBasicInfo t) {
        String sql = "update t_stock id = ?, name = ?, type = ?";
        Object[] parameters = new Object[]{t.getId(), t.getName(), t.getType()};
        return DaoHandle.executeUpdate(sql, parameters) == 1 ? true : false;
    }

    @Override
    public boolean addOrUpdate(StockBasicInfo t) {
        if (getObjectByName(t.getId()) == null) {
            return add(t);
        } else {
            return update(t);
        }
    }

    @Override
    public StockBasicInfo getObjectById(int id) {
        String sql = "select * from t_stock where id = ?";
        Object[] parameters = new Object[]{id};
        return getObject(sql, parameters);
    }

    @Override
    public StockBasicInfo getObject(String sql, Object[] parameters) {
        List<StockBasicInfo> list = DaoHandle.select(sql, parameters, StockBasicInfo.class);
        return list == null ? null : list.get(0);
    }

    @Override
    public StockBasicInfo getObjectByName(String khdm) {
        String sql = "select * from t_stock where khdm = ?";
        Object[] parameters = new Object[]{khdm};
        return getObject(sql, parameters);
    }

    @Override
    public List<StockBasicInfo> getObjectsByName(String khdm) {
        String sql = "select * from t_stock where khdm = ?";
        Object[] parameters = new Object[]{khdm};
        return getObjects(sql, parameters);
    }

    @Override
    public List<StockBasicInfo> getObjects() {
        String sql = "select * from t_stock order by id asc";
        Object[] parameters = new Object[]{};
        return getObjects(sql, parameters);
    }

    @Override
    public List<StockBasicInfo> getObjects(String sql, Object[] parameters) {
        return DaoHandle.select(sql, parameters, StockBasicInfo.class);
    }

}