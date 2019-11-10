package com.jeffosmond.ssm.controller;

import com.jeffosmond.ssm.po.Item;
import com.jeffosmond.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;

	@RequestMapping("queryItem")
	@ResponseBody
	public List<Item> queryItem(){
		return service.queryItemList();
	}
}
