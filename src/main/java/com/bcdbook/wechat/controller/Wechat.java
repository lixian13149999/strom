package com.bcdbook.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcdbook.wechat.service.ConnectService;
import com.bcdbook.wechat.service.impl.CoreServiceImpl;

@Controller
@RequestMapping("/wechat")
public class Wechat {
	@Resource
	private ConnectService connectService;
	@Resource
	private CoreServiceImpl coreService;
	
	private Logger log = Logger.getLogger(Wechat.class);  
	  
    /** 
     * 校验信息是否是从微信服务器发过来的。 
     *  
     * @param weChat 
     * @param out 
     */  
//    @RequestMapping(method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")  
    @RequestMapping(method = {RequestMethod.GET})  
    public void get(HttpServletRequest request,HttpServletResponse response) {
    	PrintWriter out = null;
    	try {
			out = response.getWriter();
			if(connectService.safeuuid(request)){
				out.print(request.getParameter("echostr"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.flush();  
			out.close();  
		}
        
    }  
    
    /** 
     * 微信消息的处理 
     *  
     * @param request 
     * @param out 
     * @throws IOException 
     */  
//    @RequestMapping(method = { RequestMethod.POST }, produces = "application/xml;charset=UTF-8")  
    @RequestMapping(method = RequestMethod.POST)
    public void post(HttpServletRequest request, HttpServletResponse response) {
//    	System.out.println("进入wechat POST请求");
        /* 消息的接收、处理、响应 */  
  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息,
        //并返回要发送给客户的信息
        String respMessage = coreService.processRequest(request);  
//        log.info(respMessage);
        // 响应消息  
        PrintWriter out = null;
		try {
//			System.out.println("Controller将要输出的xml值");
//			System.out.println(respMessage);
			out = response.getWriter();
			out.print(respMessage);  
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
			out = null;
		}
    } 
    
    @RequestMapping(value="/login", method = {RequestMethod.GET}) 
	public String getLoginPage(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("进入登录页面");
    	String code = request.getParameter("code");
    	String state = request.getParameter("code");
    	System.out.println("code:"+code);
    	System.out.println("state:"+state);
		return "login";
	}
    
    @RequestMapping("/createMenu")
    @ResponseBody
	public String getUser(HttpServletRequest request){
    	connectService.createMenu();
		return "user/getUser";
	}
    
    @RequestMapping("/getCode")
    public String getCode(HttpServletRequest request, HttpServletResponse response){
    	System.out.println("进入getCode");
    	String getCodeUrl = connectService.getCode();
    	try {
    		System.out.println("将要执行转发");
			response.sendRedirect(getCodeUrl);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    
    @RequestMapping("/getTest")
    public String getTest(HttpServletRequest request, HttpServletResponse response){
    	return "wechat/test";
    }
	
}

