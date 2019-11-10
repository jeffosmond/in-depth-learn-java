package com.jeffosmond.ssm.service.impl;

import com.jeffosmond.ssm.mapper.ItemMapper;
import com.jeffosmond.ssm.po.Item;
import com.jeffosmond.ssm.po.ItemExample;
import com.jeffosmond.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;
	
	@Override
	public List<Item> queryItemList() {
		ItemExample example = new ItemExample();
		List<Item> list = mapper.selectByExample(example);
		return list;
	}

}
