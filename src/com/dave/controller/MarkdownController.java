package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MarkdownController {

	@RequestMapping("/markdownTest")
	public String markdownTest(Model model, @RequestParam("markdownContent") String markdownContent ){
		model.addAttribute("markdownContent", markdownContent);
		return "index";
	}

	@RequestMapping("/edit")
	public String edit() {
		return "edit";
	}
	
}
