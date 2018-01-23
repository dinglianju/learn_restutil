package com.dlj.demo.learn_restutil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlj.demo.learn_restutil.beans.ResultBean;
import com.dlj.demo.learn_restutil.interfaces.IRequestDemo;

@Service
public class TestService {

	@Autowired
	IRequestDemo demo;
	
	public void test() {
		//调用接口得到结果
		ResultBean get1 = demo.get1();
		ResultBean get2 = demo.getWithKey("key--------");
	}
}
