package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dave.util.FileUtils;

@Controller
public class MarkdownController {
	
	@RequestMapping("/edit")
	public String edit() {
		return "edit";
	}

	@RequestMapping("/saveMarkDown")
	public String saveMarkDown(Model model, @RequestParam("markdownContent") String markdownContent, @RequestParam("markdownName") String markdownName){
		FileUtils.exportMarkDown(markdownName, markdownContent);
		model.addAttribute("markdownContent", markdownContent);
		return "index";
	}
	
	@RequestMapping("/getMarkDown")
	public String getMarkDown(Model model){
		String fileName = "markdown";
		String markdownContent = FileUtils.getFileData(fileName);
		model.addAttribute("markdownContent", markdownContent);
		return "index";
	}
	
}
