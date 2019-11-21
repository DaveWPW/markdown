package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.util.FileUtils;

@Controller
@RequestMapping("/")
public class PageController {

	@RequestMapping("doIndexUI")
	public String doIndexUI(Model model) {
		String fileName = "README";
		String markdownContent = FileUtils.getFileData(fileName);
		model.addAttribute("markdownContent", markdownContent);
		model.addAttribute("fileName", fileName);
		return "index";
	}
	
}
