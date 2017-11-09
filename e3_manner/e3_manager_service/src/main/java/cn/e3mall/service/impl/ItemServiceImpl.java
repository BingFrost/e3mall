package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public DataGridResult getItemByPage(Integer page , Integer rows){
		//设置分页参数
		PageHelper.startPage(page, rows);
		//设置查询条件
		TbItemExample example = new TbItemExample();
		//分页查询
		List<TbItem> list = itemMapper.selectByExample(example);
		//获取分页所需参数:总页码
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		//设置DataGridResult
		DataGridResult rustlt = new DataGridResult();
		rustlt.setTotal(total);
		rustlt.setRows(list);
		
		return rustlt;
	}
}
