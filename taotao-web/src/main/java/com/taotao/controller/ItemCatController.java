package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.service.ItemCatService;

/**
 * 商品类目
 * 
 * @author Junjunlei
 *
 */
@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping("list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(defaultValue="0",value="id")Long parentId){
		return itemCatService.getItemCatList(parentId);
		
	}
}
