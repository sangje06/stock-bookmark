package com.sj.stbm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sj.stbm.config.PropsConfig;

@Controller
public class BookmarkController {

	@Autowired
	PropsConfig propsConfig;
	
	@GetMapping("/")
	public String getBookmark(Model model) {
		Map<String, String> orgMap = propsConfig.getOrgs();
		model.addAttribute("orgMap", orgMap);
		return "/bookmark/bookmark";
	}

}
