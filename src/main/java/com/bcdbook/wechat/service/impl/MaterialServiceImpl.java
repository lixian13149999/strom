package com.bcdbook.wechat.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bcdbook.enums.WechatEnum.IWechatEnum;
import com.bcdbook.wechat.service.MaterialService;
import com.bcdbook.wechat.util.WechatRequest;

/**
 * 
     * @Title: MaterialServiceImpl.java    
     * @Description: 素材管理的实现类
     * @author lason       
     * @created 2016年5月30日 上午10:21:45
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

	/**
	 * 
	    * @Discription 获取素材列表
	    * @author lason       
	    * @created 2016年5月30日 上午10:22:25      
	    * @return     
	    * @see com.bcdbook.wechat.service.MaterialService#listMaterial()
	 */
	@Override
	public String listMaterial(String materialType) {
		String ACCESS_TOKEN = IWechatEnum.ACCESS_TOKEN.getValue();
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token="+ACCESS_TOKEN;
		String postValue = "{\"type\":\""+materialType+"\",\"offset\":0,\"count\":20}";
		
		JSONObject tokenJson = null;
		
		try {
			String tokenBack = WechatRequest.wechatPost(url, postValue);
			System.out.println(tokenBack);
			if(tokenBack!=null){
				tokenJson = JSON.parseObject(tokenBack);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String tokenString = JSON.toJSONString(tokenJson);
//		System.out.println(tokenString);
//		System.out.println(tokenJson);
		return null;
	}
	
	@Override
	public String getMaterial(String mediaId) {
		String ACCESS_TOKEN = IWechatEnum.ACCESS_TOKEN.getValue();
		//定义请求的url
		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token="+ACCESS_TOKEN;
		String postValue = "{\"media_id\":\""+mediaId+"\"}";
		
		JSONObject tokenJson = null;
		
		try {
			String tokenBack = WechatRequest.wechatPost(url, postValue);
			System.out.println(tokenBack);
			if(tokenBack!=null){
				tokenJson = JSON.parseObject(tokenBack);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String tokenString = JSON.toJSONString(tokenJson);
		System.out.println(tokenString);
//		System.out.println(tokenJson);
		// TODO Auto-generated method stub
		return null;
	}

}
