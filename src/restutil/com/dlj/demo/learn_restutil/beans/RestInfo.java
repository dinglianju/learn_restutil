package com.dlj.demo.learn_restutil.beans;

import lombok.Data;

/*
 * 包装服务器信息类，目前只有host，其他自己配置即可
 */
@Data
public class RestInfo {

	private String host;
}
