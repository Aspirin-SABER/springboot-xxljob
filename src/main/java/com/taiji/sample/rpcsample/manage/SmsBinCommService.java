/** 
* @author zhangyu
* @version 2017年5月27日 下午5:16:56 
*/ 
package com.taiji.sample.rpcsample.manage;

import java.io.IOException;


import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.sms.model.comm.protocol.SendSmsRequest;
import cn.com.taiji.sms.model.comm.protocol.SendSmsResponse;

/**
 * @author zhangyu
 *下午5:16:56
 * Email zhangyud@mail.taiji.com.cn
 */
public interface SmsBinCommService
{
	/**
	 * 调用SMS系统的服务发送短信
	 * 
	 * @param request
	 *            发送短信的请求
	 * @return
	 * @throws IOException
	 *             http通信错误抛出IO异常
	 * @throws
	 *
	 */
	public SendSmsResponse sendSms(SendSmsRequest request) throws IOException, ApiRequestException;
}
