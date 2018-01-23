package com.dlj.demo.learn_restutil.services;

import com.dlj.demo.learn_restutil.beans.ResultBean;
import com.dlj.demo.learn_restutil.http.GET;
import com.dlj.demo.learn_restutil.http.Param;
import com.dlj.demo.learn_restutil.http.Rest;

/*
 * 业务代码
 * 里面调用一些http接口
 */
//@Rest(value="http://localhost:8081/test")
public abstract class SomeService {

	/*
	 * 在类里面增加一个抽象的http接口调用方法
	 */
	@GET("/get2")
	public abstract ResultBean get2(@Param("key") String key);
	
	/*
	 * 业务代码类中直接调用其他系统的http接口
	 */
	public String doSomething() {
		return "调用接口返回的结果： " + get2("支持直接在类里面注入使用").getData();
	}
}
