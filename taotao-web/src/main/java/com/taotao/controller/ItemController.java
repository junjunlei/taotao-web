package com.taotao.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.ItemService;
import com.taotao.utils.FastDFSClient;

/**
 * 商品controller
 * 
 * @author Junjunlei
 *
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@Value("${IMAGE_ADDRESS}")
	private String IMAGE_ADDRESS;

	/**
	 * 根据当前页和每页显示条数查询商品信息
	 * 
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;

	}
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	//解决火狐兼容性问题  必须转换为Json 字符串  而不是json对象
	public  String uploadImg(MultipartFile uploadFile) {
		PictureResult result=new PictureResult();
		if(uploadFile.isEmpty()) {//图片为空
			result.setError(1);
			result.setMessage("图片为空");
			return JsonUtils.objectToJson(result);
		}
		//上传到服务器
		try {
			FastDFSClient client=new FastDFSClient("classpath:/properties/fastdfs.conf");
			String filename = uploadFile.getOriginalFilename();
			//文件上传
			String filePath = client.uploadFile(uploadFile.getBytes(),filename.substring(filename.lastIndexOf(".")+1));
			String url=IMAGE_ADDRESS+filePath;
			result.setError(0);
			result.setUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return JsonUtils.objectToJson(result);
		
	}

}
