/*
 * Date: 2016年2月23日
 * author: Peream  (peream@gmail.com)
 *
 */
package com.taiji.sample.rpcsample.manage;

import java.io.IOException;

import cn.com.taiji.common.manager.net.http.binclient.AbstractBinCommManager;
import cn.com.taiji.common.manager.net.http.binclient.ApiRequestException;
import cn.com.taiji.common.manager.net.http.binclient.Response2Modelhandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;


import cn.com.taiji.sms.model.comm.protocol.SendSmsRequest;
import cn.com.taiji.sms.model.comm.protocol.SendSmsResponse;
import cn.com.taiji.sms.model.comm.protocol.SmsServiceType;

/**
 * 
 * @author Peream <br>
 *         Create Time：2016年2月23日 下午3:38:15<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Service
@PropertySource(value = "classpath:comm.properties",ignoreResourceNotFound = true)
public class SmsBinCommServiceImpl extends AbstractBinCommManager implements SmsBinCommService
{

	@Autowired
	public SmsBinCommServiceImpl()
	{
		super("http://192.168.80.50/jtwsms/app/common/binapi");
	}

	@Override
	public SendSmsResponse sendSms(SendSmsRequest request) throws IOException, ApiRequestException
	{
		String reqName = SmsServiceType.SMS.name() + "_SEND_REQ_" + getTimeMillStr() + ".json";
		return filePost(reqName, request, new Response2Modelhandler<>(SendSmsResponse.class));
	}

}
