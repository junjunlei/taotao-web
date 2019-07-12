package com.taotao.fastdfs;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastdfsTest {
	@Test
	public void testUpload() throws Exception {

		//初始化全局配置  加载一个配置文件
		ClientGlobal.init("D:\\GIT\\taotao\\taotao-web\\taotao-web\\src\\main\\resources\\poperties\\fastdfs.conf");
		//创建TrackerClient 对象iang
		TrackerClient trackerClient=new TrackerClient();
		//创建一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//声明StorageServer对象  null
        StorageServer storageServer=null;
        //获得StorageClient
        StorageClient sClient=new StorageClient(trackerServer,storageServer);
        //
        String[] strings = sClient.upload_appender_file("C:\\Users\\Junjunlei\\Pictures\\Saved Pictures\\11.jpg", "jpg", null);
        for(String string :strings) {
        	System.out.println(string);
        }
	}
	
	@Test
	public void testUploadFile() throws Exception {
		FastDFSClient client=new FastDFSClient("classpath:/properties/fastdfs.conf");
		String string = client.uploadFile("C:\\\\Users\\\\Junjunlei\\\\Pictures\\\\Saved Pictures\\\\11.jpg","jpg");
		System.out.println(string);
	}
}
