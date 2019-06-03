package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;

	/**
	 * 根据父节点查询子节点
	 * 
	 * @param parentId 父节点id
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.getContentCategoryList(parentId);
	}

	/**
	 * 添加节点
	 * 
	 * @param parentId 父节点 id
	 * @param name  节点名称
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addContentCategory(Long parentId, String name) {
		return contentCategoryService.addContentCategory(parentId, name);
	}
	/**
	 * 更新节点
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id, String name) {
		return contentCategoryService.updateContentCategory(id, name);
	}
	/**
	 *  删除节点
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id) {
		return contentCategoryService.deleteContentCategory(id);
	}
}
