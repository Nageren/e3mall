package cn.e3.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3.utils.FastDFSClient;

public class MyFastDFS {
	/**
	 * 需求:使用fastDFs提供的客户端api实现图片上传
	 */
	/*
	 * 上传步骤分析:
	 * 1.指定图片的上传图片路径
	 * 2.指定图片服务器的地址,使用Client.conf配置文件上传
	 * 3.加载图片服务器,连接图片服务器
	 * 4.创建图片服务器tracker客户端
	 * 5.从客户端获取tracker连接
	 * 6.创建storage客户端,存储图片
	 * 
	 */
	@Test
	public  void  uploadPicTest01() throws Exception{
		//1.指定上传图片的绝对路径
		String pic = "F:\\www\\1.jpg";
		
		//2.指定服务器路径指定Client.conf绝对路径
		String clientPath = "E:\\Work\\project2\\e3mall\\"
				+ "e3-manager-web\\src\\main\\resources\\conf\\client.conf";
		
		//3.加载配置文件,连接服务器
		ClientGlobal.init(clientPath);
		
		//4.创建tracker客户端
		TrackerClient tClient = new TrackerClient();
		
		//5.从客户端获取tracker对象
		TrackerServer trackerServer = tClient.getConnection();
		
		StorageServer storageServer=null;
		//6.创建storageClient客户端
		StorageClient sClient = new StorageClient(trackerServer, storageServer);
		
		//7.上传
		String[] urls = sClient.upload_file(pic, "jpg", null);
		
		for (String url : urls) {
			
			System.out.println(url);
		}
	}
	
	
	
	@Test
	public  void  uploadPicTest02() throws Exception{
		//1.指定上传图片的绝对路径
		String pic = "F:\\www\\2.jpg";
		//2.指定服务器路径指定Client.conf绝对路径
		String clientPath = "E:\\Work\\project2\\e3mall\\"
				+ "e3-manager-web\\src\\main\\resources\\conf\\client.conf";
		FastDFSClient fastDFSClient = new FastDFSClient(clientPath);
		
		String url = fastDFSClient.uploadFile(pic);
		
		System.out.println(url);
	}
}
