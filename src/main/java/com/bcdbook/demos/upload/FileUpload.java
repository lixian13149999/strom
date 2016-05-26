package com.bcdbook.demos.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUpload {
	@RequestMapping("/toPage")
	public String toUpload(HttpServletRequest request,Model mode){
		return "test/upload";
	}
	
	@RequestMapping("/doUpload")
	@ResponseBody
	public String function(@RequestParam MultipartFile myfiles) {
		//获取原始的名字,获取上传路径的名字
	    String filename = myfiles.getOriginalFilename();
	    System.out.println("filename:"+filename);
	    //根据上传文件的名字设置上传到服务器的路径
//	    File source = new File(filename);
	    File source = new File("/Users/lason/java", filename);
	    System.out.println(source.getPath());
	    try {
	    	myfiles.transferTo(source);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return "upload seccess";
	}
	
	/**
	 * 这里这里用的是MultipartFile[] myfiles参数,所以前台就要用<input type="file"
	 * name="myfiles"/>
	 * 上传文件完毕后返回给前台[0`filepath],0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
	 */
	@RequestMapping(value = "/fileUpload")
	@ResponseBody
	public String addUser(@RequestParam("uname") String uname,
			@RequestParam MultipartFile[] myfiles, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 可以在上传文件的同时接收其它参数
		// System.out.println("收到用户[" + uname + "]的文件上传请求");

		// 通过request获取项目的根路径,并把设定值放到getRealPath中,用来设定目标路径
		String targetPath = request.getSession().getServletContext()
				.getRealPath("/upload");

		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input
		// type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		for (MultipartFile myfile : myfiles) {
			// 检查文件是否存在
			if (myfile.isEmpty()) {
				return "{\"msg\":\"未选中文件\"}";
			} else {
				// 根据文件对象,获取文件的名字
				originalFilename = myfile.getOriginalFilename();
				try {
					// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
					// 此处也可以使用Spring提供的MultipartFile.transferTo(File,dest)方法实现文件的上传
					//这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							new File(targetPath, originalFilename));
				} catch (IOException e) {
					e.printStackTrace();
					return "{\"msg\":\"上传失败\"}";
				}
			}
		}
		
		return "{\"msg\":\"seccess\"}";
	}
}
