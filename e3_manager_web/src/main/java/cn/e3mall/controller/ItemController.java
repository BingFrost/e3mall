package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	//进入首页
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	//一级页面跳转
	@RequestMapping("/{page}")
	public String getItemById(@PathVariable String page){
		return page;
	}
	//商品列表分页查询
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemByPage(Integer page , Integer rows){
		DataGridResult result = itemService.getItemByPage(page, rows);
		return result;
	}
	
	//添加商品
	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result addItem(TbItem item , String desc){
		E3Result result = itemService.addItem(item, desc);
		return result;
	}
}
