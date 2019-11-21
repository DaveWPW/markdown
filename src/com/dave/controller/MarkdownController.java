package com.dave.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.util.FileUtils;

import common.vo.JsonResult;

@Controller
@RequestMapping("/")
public class MarkdownController {
	
	@RequestMapping("doGetMarkdownUI")
	public String doGetMarkdownUI(Model model){
		String fileName = "markdown";
		String markdownContent = FileUtils.getFileData(fileName);
		model.addAttribute("markdownContent", markdownContent);
		return "markdown";
	}
	
	@RequestMapping("doMarkdownEditUI")
	public String doMarkdownEditUI(Model model) {
		return "system/markdown_edit";
	}
	
	@RequestMapping("doMarkdownSave")
	public String doMarkdownSave(Model model, @RequestParam("markdownContent") String markdownContent, @RequestParam("markdownName") String markdownName){
		FileUtils.exportMarkDown(markdownName, markdownContent);
		model.addAttribute("markdownContent", markdownContent);
		return "markdown";
	}
	
	@RequestMapping("doImageUpload")
    public void doImageUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file,
            HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter wirte = null; 
        JSONObject json = new JSONObject();
        try {
            wirte = response.getWriter();
            //文件存放的路径
            String path = request.getSession().getServletContext().getRealPath("upload");
            String url = "http://localhost:8080" + request.getContextPath() + "/upload/"
            		+ FileUtils.upload(file, path); 
            json.put("success", 1);
            json.put("message", "hello");
            json.put("url", url);
        } catch (Exception e) {  
        } finally {
        	wirte.print(json);
        	wirte.flush();
        	wirte.close();
    	}
    }
	
	@RequestMapping("doGetMarkdownData")
	@ResponseBody
	public JsonResult doGetMarkdownData(String fileName){
		String markdownContent = FileUtils.getFileData(fileName);
		JsonResult jsonResult = new JsonResult();
		jsonResult.setData(markdownContent);
		jsonResult.setState(1);
		return jsonResult;
	}
	
}
