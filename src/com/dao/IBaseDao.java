package com.dao;

import java.util.List;

public interface IBaseDao<T> {

	// 保存实体
	boolean add(T t);

	// 删除实体
	boolean remove(T t);

	// 根据id删除
	boolean remove(String id);

	// 更新实体数据
	boolean update(T t);

	// 添加或更新，有则更新，无则添加
	boolean addOrUpdate(T t);

	// 用id获取一个实体
	T getObjectById(int id);

	// 用khdm获取一个实体
	T getObjectByName(String khdm);

	// 自定义获取一个实体
	T getObject(String sql, Object[] parameters);

	// 自定义获取一个集合
	List<T> getObjects(String sql, Object[] parameters);

	// 根据khdm返回集合
	List<T> getObjectsByName(String khdm);

	// 获取所有实体
	List<T> getObjects();
}
