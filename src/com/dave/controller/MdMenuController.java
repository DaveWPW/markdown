package com.dave.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.vo.JsonResult;
import common.vo.Node;

@Controller
@RequestMapping("/md_menu/")
public class MdMenuController {
		
	@RequestMapping("doMdMenuListUI")
	public String doMdMenuListUI() {
		return "system/md_menu_list";
	}
	
	@RequestMapping("doMdMenuEditUI")
	public String doMdMenuEditUI() {
		return "system/md_menu_edit";
	}
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		List<Map<String, Object>> mdMenus = new ArrayList<>();
		
		Map<String, Object> mdMenu = new HashMap<String, Object>();
		mdMenu.put("menuId", 1);
		mdMenu.put("menuNum", 1);
		mdMenu.put("menuName", "在线文档");
		mdMenu.put("parentNum", 0);
		mdMenu.put("parentName", null);
		mdMenu.put("menuType", 1);
		mdMenu.put("fileName", null);
		mdMenus.add(mdMenu);
		
		Map<String, Object> mdMenu1 = new HashMap<String, Object>();
		mdMenu1.put("menuId", 2);
		mdMenu1.put("menuNum", 2);
		mdMenu1.put("menuName", "Spring介绍");
		mdMenu1.put("parentNum", 1);
		mdMenu1.put("parentName", "在线文档");
		mdMenu1.put("menuType", 2);
		mdMenu1.put("fileName", "Spring");
		mdMenus.add(mdMenu1);
		
		Map<String, Object> mdMenu2 = new HashMap<String, Object>();
		mdMenu2.put("menuId", 3);
		mdMenu2.put("menuNum", 3);
		mdMenu2.put("menuName", "Docker介绍");
		mdMenu2.put("parentNum", 1);
		mdMenu2.put("parentName", "在线文档");
		mdMenu2.put("menuType", 2);
		mdMenu2.put("fileName", "Docker");
		mdMenus.add(mdMenu2);
		
		return new JsonResult(mdMenus);
	}
	
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	public JsonResult doFindZtreeMenuNodes() {
		List<Node> nodes = new ArrayList<>();
		Node node = new Node();
		node.setId(1);
		node.setName("在线文档");
		node.setParentId(0);
		nodes.add(node);
		
		Node node1 = new Node();
		node1.setId(2);
		node1.setName("Spring介绍");
		node1.setParentId(1);
		nodes.add(node1);
		
		Node node2 = new Node();
		node2.setId(3);
		node2.setName("Docker介绍");
		node2.setParentId(1);
		nodes.add(node2);
		
		return new JsonResult(nodes);
	}
	
}
