package cn.e3mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	//根据patentID进行自查询
	@Override
	public List<TreeNode> getItemCatList(long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		//组装查询条件
		Criteria criteria = example.createCriteria();
		//设置查询参数
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example );
		//把分类列表转换成TreeNode列表
		List<TreeNode> result  = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			//如果节点是父节点那么状态就是closed，如果是叶子节点那么状态就是open
			treeNode.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表
			result.add(treeNode);
		}
		// 返回结果
		return result;
	}

}
