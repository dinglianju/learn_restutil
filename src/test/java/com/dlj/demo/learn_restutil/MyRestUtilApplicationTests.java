package com.dlj.demo.learn_restutil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dlj.demo.learn_restutil.beans.ResultBean;
import com.dlj.demo.learn_restutil.interfaces.IRequestDemo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRestUtilApplication.class)
public class MyRestUtilApplicationTests {

	@Autowired
	IRequestDemo demo;
	
	@Test
	public void test() {
		ResultBean get1 = demo.get1();
		System.out.println("----: " + get1.toString());
	}
	
	@Test
	public void test2() {
		ResultBean get2 = demo.getWithKey("232323");
		System.out.println("----: " + get2.toString());
	}
}
