package com.sj.stbm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookmarkController {
	
	@GetMapping("/")
	public String getBookmark() {
		return "/bookmark/bookmark";
	}

}
