package com.sj.stbm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.stbm.service.StockService;

@Controller
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping("/stock/list")
	@ResponseBody
	public Map<String, String> getList() {
		return stockService.getStockList();
	}
	
	

}
