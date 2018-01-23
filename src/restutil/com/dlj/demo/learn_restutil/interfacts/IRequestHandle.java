package com.dlj.demo.learn_restutil.interfacts;

import com.dlj.demo.learn_restutil.beans.RequestInfo;
import com.dlj.demo.learn_restutil.beans.RestInfo;

/*
 * 处理网络请求接口
 */
public interface IRequestHandle {

	Object handle(RestInfo restInfo, RequestInfo request);
}
