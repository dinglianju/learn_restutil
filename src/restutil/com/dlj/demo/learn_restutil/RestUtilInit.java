package com.dlj.demo.learn_restutil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.dlj.demo.learn_restutil.beans.RequestInfo;
import com.dlj.demo.learn_restutil.beans.RestInfo;
import com.dlj.demo.learn_restutil.http.GET;
import com.dlj.demo.learn_restutil.http.Param;
import com.dlj.demo.learn_restutil.http.Rest;
import com.dlj.demo.learn_restutil.interfacts.IRequestHandle;

@Component
public class RestUtilInit {

	@Autowired
	IRequestHandle requestHandle;
	
	@PostConstruct
	public void init() {
		System.out.println("enter init------>>");
		Set<Class<?>> requests = new Reflections("com.dlj").getTypesAnnotatedWith(Rest.class);
		
		for (Class<?> cls : requests) {
			createProxyClass(cls);
		}
	}
	
	private void createProxyClass(Class<?> cls) {
		System.err.println("\tcreate proxy for class:" + cls);
		
		//rest 服务器相关信息
		final RestInfo restInfo = extractRestInfo(cls);
		System.err.println("restInfo :  " + restInfo);
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				RequestInfo request = extractRequestInfo(method, args);
				System.err.println("requestInfo: " + request);
				return requestHandle.handle(restInfo, request);
			}
		};
		
		//创建动态代理类
		Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] {cls}, handler);
		registerBean(cls.getName(), proxy);
	}
	
	private RestInfo extractRestInfo(Class<?> cls) {
		RestInfo restinfo = new RestInfo();
		Rest annotation = cls.getAnnotation(Rest.class);
		String host = annotation.value();
		restinfo.setHost(host);
		return restinfo;
	}
	
	private RequestInfo extractRequestInfo(Method method, Object[] args) {
		RequestInfo info = new RequestInfo();
		// TODO 目前只写了get请求，需要支持post请求等在这里增加
		GET annotation = method.getAnnotation(GET.class);
		
		String url = annotation.value();
		System.out.println("----------url: " + url);
		//没有配置路径取函数名称
		if (StringUtils.isEmpty(url)) {
			url = "/" + method.getName();
		}
		info.setUrl(url);
		//返回类型
		info.setReturnType(method.getReturnType());
		//参数
		LinkedHashMap<String, String> params = extractParams(method, args);
		info.setParams(params);
		return info;
	}
	
	private LinkedHashMap<String, String> extractParams(Method method, Object[] args) {
		Parameter[] parameters = method.getParameters();
		
		if (parameters.length == 0)
			return null;
		
		LinkedHashMap<String, String> params = new LinkedHashMap<>();
		for (int i = 0; i < parameters.length; i++) {
			/*
			 * 需要考虑变量名映射功能
			 * 编译时必须加上 -g 参数才会生成方法参数名
			 * parameters[i].getName() 得到的结果是arg0
			 */
			Param param = parameters[i].getAnnotation(Param.class);
			
			if (param != null) {
				System.out.println("---------param.value: " + param.value() + "; args[i]: " + String.valueOf(args[i]));
				params.put(param.value(), String.valueOf(args[i]));
			}
		}
		return params;
	}
	
	@Autowired
	ApplicationContext ctx;
	
	public void registerBean(String name, Object obj) {
		//获取BeanFactory
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
		//动态注册bean
		defaultListableBeanFactory.registerSingleton(name, obj);
	}
}
