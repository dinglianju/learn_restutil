package com.dlj.demo.learn_restutil.interfaces;

import com.dlj.demo.learn_restutil.beans.ResultBean;
import com.dlj.demo.learn_restutil.http.GET;
import com.dlj.demo.learn_restutil.http.Param;
import com.dlj.demo.learn_restutil.http.Rest;

@Rest("http://localhost:8081/testneedlogin")
public interface IRequestNeedLoginDemo {
	@GET("/get1")
	ResultBean get1();
	
	@GET("/get2")
	ResultBean getWithKey(@Param("key") String key);

}
