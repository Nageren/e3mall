package cn.e3.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3.utils.FastDFSClient;
import cn.e3.utils.JsonUtils;
import cn.e3.utils.KindEditorMdoel;

@Controller
public class UploadController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	/**
	 * 需求分析:
	 *  请求: /pic/upload
	 * 	请求参数: 上传参数文件参数名称 uploadFile
	 * 	返回值:
	 *    成功时:{"error" : 0,"url" : "http://www.example.com/path/to/1.jpg"}
	 *	    失败时:{"error" : 1, "message": "上传失败"}
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uplaodPic(MultipartFile uploadFile){
		try {
			//获取上传文件的名称
			String fileName = uploadFile.getOriginalFilename();
			
			String extName =  fileName.substring( fileName.lastIndexOf(".")+1);
			
			//创建FastClient工具类对象
			FastDFSClient fClient = new FastDFSClient("classpath:conf/client.conf");
			//上传  返回图片的地址   group1/M00/00/00/wKhBl1oqD3yABL51AABxQeNevOI826.jpg
			String url = fClient.uploadFile(uploadFile.getBytes(), extName);
			//组合服务器图片地址
			url = IMAGE_SERVER_URL + url;
			
			//创建kindEditorMdoel对象封装返回值
			KindEditorMdoel model = new KindEditorMdoel();
			model.setError(0);
			model.setUrl(url);
			
			//转换json
			String picJson = JsonUtils.objectToJson(model);
			return picJson;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			KindEditorMdoel model = new KindEditorMdoel();
			model.setError(1);
			model.setMessage("上传失败,请重试");
			
			String picJson = JsonUtils.objectToJson(model);
			return picJson;
		}
	}
	
}	
