package cn.e3mall.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

@Controller
public class PictureController {

	@Value("${imageservice.url}")
	private String imageServiceUrl;

	
	@RequestMapping(value="/pic/upload", produces="text/plain;chart=utf-8")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile){
		//1.获取文件名称
		String filename = uploadFile.getOriginalFilename();
		//2.切割名称,取文件扩展名
		String substring = filename.substring(filename.lastIndexOf(".")+1);
		Map result = new HashMap<>();
		try {
			//3.新建一个文件上传客户端对象,需要读取配置文件
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//4.执行上传处理,第一个参数是上传文件的byte数组(getBytes()即可)
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), substring);
			//5.将文件所在数据库中的URL拼接完整
			url=imageServiceUrl+url;
			//6.返回map
			result.put("error", 0);
			result.put("url", url);
		}  catch (Exception e) {
			result.put("error", 1);
			result.put("message","图片上传失败");
			e.printStackTrace();
		}
		return JsonUtils.objectToJson(result);
	}
}
