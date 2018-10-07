package com.bean;

//该类为股票的基本信息
public class StockBasicInfo {
	private String id;//股票代码
	private String name;//股票名称
	private String type;//上海交易所为1，深圳交易所为2
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
