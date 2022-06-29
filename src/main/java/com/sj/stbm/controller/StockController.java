package com.sj.stbm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StockController {
	
	@GetMapping("/stock/list")
	@ResponseBody
	public String getList() {
		return "String";
	}

}
