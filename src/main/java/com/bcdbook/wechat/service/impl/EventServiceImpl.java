package com.bcdbook.wechat.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.wechat.pojo.Message;
import com.bcdbook.wechat.service.EncapsulationService;
import com.bcdbook.wechat.service.EventService;

/**
 * 
 * @Title: EventServiceImpl.java
 * @Description: 处理事件消息的方法
 * @author lason
 * @created 2016年5月27日 上午10:42:46
 */
@Service("eventService")
public class EventServiceImpl implements EventService {
	@Resource
	private EncapsulationService encapsulationService;

	/**
	 * 
	 * @Discription 处理事件的主控制器,由主控制器来控制调用哪个方法,处理相应的事件
	 * @author lason
	 * @created 2016年5月27日 上午10:43:16
	 * @param msg
	 * @return
	 * @see com.bcdbook.wechat.service.EventService#processEvent(java.util.Map)
	 */
	@Override
	public Message processEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processSubscribeEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processUnSubscribeEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processSCANEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processLOCATIONEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processCLICKEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message processVIEWEvent(Map<String, String> msg) {
		// TODO Auto-generated method stub
		return null;
	}
}
