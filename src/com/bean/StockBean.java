package com.bean;

import java.util.List;

//该类同 发送请求返回的数据JSON 相对应
public class StockBean {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<String> getFlow() {
		return flow;
	}
	public void setFlow(List<String> flow) {
		this.flow = flow;
	}
    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

	private String code;
	private Info info;
    private List<String>data;
	private List<String>flow;
}
