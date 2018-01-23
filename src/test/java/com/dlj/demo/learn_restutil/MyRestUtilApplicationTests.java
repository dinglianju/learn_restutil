package com.dlj.demo.learn_restutil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dlj.demo.learn_restutil.beans.ResultBean;
import com.dlj.demo.learn_restutil.interfaces.IRequestDemo;
import com.dlj.demo.learn_restutil.interfaces.IRequestNeedLoginDemo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRestUtilApplication.class)
public class MyRestUtilApplicationTests {

	@Autowired
	IRequestDemo demo;
	
	@Autowired
	IRequestNeedLoginDemo loginDemo;
	
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
	
	@Test
	public void test3() {
		ResultBean get3 = demo.getWithMultKey("param1", "param2");
		System.out.println("----: " + get3);
	}
	
	@Test
	public void testNeedLoginDemo1() {
		ResultBean get1 = loginDemo.get1();
		System.out.println("needlogindemo: " + get1);
	}
	
	@Test
	public void testNeedLoginDemo2() {
		ResultBean get2 = loginDemo.getWithKey("login in");
		System.out.println("needlogindemo key" + get2);
	}
}
