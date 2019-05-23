package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.TestService;

/**
 * 测试使用的 controller 查询数据库当前时间
 * 
 * @author Junjunlei
 *
 */
@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("queryNow")
	@ResponseBody
	public String queryNow() {
		return testService.queryNow();
	}
}
