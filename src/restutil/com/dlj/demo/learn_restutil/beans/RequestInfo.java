package com.dlj.demo.learn_restutil.beans;

import java.util.LinkedHashMap;

import lombok.Data;

/*
 * 请求信息包装类
 */
@Data
public class RequestInfo {

	private String url;
	private Class<?> returnType;
	private LinkedHashMap<String, String> params;
	
}
