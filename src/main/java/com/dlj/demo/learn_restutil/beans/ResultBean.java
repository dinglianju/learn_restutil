package com.dlj.demo.learn_restutil.beans;

import lombok.Data;

@Data
public class ResultBean {

	private int code;
	private String msg = "success";
	private String data;
}
