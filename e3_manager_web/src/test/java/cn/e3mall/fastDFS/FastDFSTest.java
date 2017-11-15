package cn.e3mall.fastDFS;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDFSTest {

	@Test
	public void testUpload() throws FileNotFoundException, IOException, MyException{
		// 1、加载配置文件,名字不做任何要求,配置文件中的内容就是tracker服务的地址。
		
		// 配置文件内容：tracker_server=192.168.25.133:22122
		ClientGlobal.init("E:/Product02/src/e3_manager_web/src/main/resources/conf/client.conf");
		// 2、创建一个TrackerClient对象。直接new一个。
		TrackerClient trackerClient = new TrackerClient();
		// 3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
		TrackerServer server = trackerClient.getConnection();
		// 4、创建一个StorageServer的引用，值为null
		StorageServer storageServer = null;
		// 5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
		StorageClient storageClient = new StorageClient(server, storageServer);
		// 6、使用StorageClient对象上传图片。
		//参数1：本地文件路径及文件名 参数2：文件扩展名，不包含“.” 参数3：元数据，可以为null
		String[] upload_file = storageClient.upload_file("E:/Ice/Pictures/2017-09-08_141945.png", "png", null);
		// 7、返回数组。包含组名和图片的路径。
		for (String string : upload_file) {
			System.out.println(string);
		}

	}
	
	@Test
	public void testUploadInUtils() throws Exception{
		//使用工具类的构造方法,形参是配置文件的绝对路径,可以写成相对路径
		FastDFSClient fastDFSClient = new FastDFSClient("E:/Product02/src/e3_manager_web/src/main/resources/conf/client.conf");
		String uploadFile = fastDFSClient.uploadFile("E:/Ice/壁纸002.jpg");
		System.out.println(uploadFile);
	}
}
