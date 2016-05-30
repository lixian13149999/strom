package com.bcdbook.wechat.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bcdbook.wechat.pojo.Message;
import com.bcdbook.wechat.service.EncapsulationService;
import com.bcdbook.wechat.service.MessageService;

/**
 * 
 * @Title: MessageServiceImpl.java
 * @Description: 处理消息的方法
 * @author lason
 * @created 2016年5月27日 上午10:22:28
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Resource
	private EncapsulationService encapsulationService;

	@Override
	public String processTextMsg(Map<String, String> msg) {
		System.out.println("接收到文本消息");

		/**
		 * 用来返回的xml格式的字符串
		 */
		String respXML = null;
		// 获取客户发送过来的文本消息
		String content = msg.get("Content");

		/*
		 * 以下是用来测试回复消息的方法,真实项目会连接数据库查询对应的返回数据
		 */
		String respContent = null;
		switch (content) {
		case "1":
			respContent = "您输入的是1";
			break;
		case "2":
			respContent = "别开玩笑,我不2";
			break;
		case "你好":
			respContent = "你好,祝你有快乐的一天";
			break;
		case "呵呵":
			respContent = "不要说脏话好不好";
			break;
		case "早":
			respContent = "早上好,祝您一天好心情";
			break;
		case "测试":
			respContent = "不要再试我了,我运行是正常的";
			break;
		case "test":
			respContent = "不要再测试我了,英文我也认识";
			break;

		default:
			respContent = "我不知道您是什么意思";
			break;
		}

		respXML = encapsulationService.encapsulationTextMsg(msg, respContent);
		System.out.println("回复的文本消息");
		System.out.println(respXML);
		return respXML;
	}

	@Override
	public String processImageMsg(Map<String, String> msg) {
		System.out.println("接收到图片消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processVoiceMsg(Map<String, String> msg) {
		System.out.println("接收到语音消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processVideoMsg(Map<String, String> msg) {
		System.out.println("接收到视频消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processShortvideoMsg(Map<String, String> msg) {
		System.out.println("接收到短视频消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processLocationMsg(Map<String, String> msg) {
		System.out.println("接收到地址消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processLinkMsg(Map<String, String> msg) {
		System.out.println("接收到连接消息");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processUnknowMsg(Map<String, String> msg) {
		System.out.println("接收到无法识别的消息");
		// TODO Auto-generated method stub
		return null;
	}

	
	//==============从此往下的方法需要立刻实现==============
	/**
	 * 
	    * @Discription 添加微信回复的消息的方法
	    * @author lason       
	    * @created 2016年5月30日 下午10:16:34      
	    * @param message
	    * @return     
	    * @see com.bcdbook.wechat.service.MessageService#addMsg(com.bcdbook.wechat.pojo.Message)
	 */
	@Override
	public String addMsg(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateMsg(Message message) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean hasMsgByName(String msgName) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean hasMsgByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return false;
	}
}
