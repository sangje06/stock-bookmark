package com.sj.stbm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.stbm.service.MyStockService;

@Controller
public class MyStockController {

	@Autowired
	private MyStockService myStockService;
	
	@PostMapping("/my/stock/save")
	@ResponseBody
	public void saveMyStock(@RequestBody Map<String, Object> param) throws JsonProcessingException {
		myStockService.saveMyStockList(param);
	}
	
	@GetMapping("/my/stock/list")
	@ResponseBody
	public Map<Object, Object> getMyStockList() {
		return myStockService.getMyStockList();
	}
}
