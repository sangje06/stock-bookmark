package com.sj.stbm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyStockController {

	@PostMapping("/my/stock/save")
	private void saveMyStock(@RequestParam Map<String, Object> param) {
		System.out.println(param);
	}
}
