package cn.e3mall.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class TestPageHelper {

	@Test
	public void testPageHelper(){
		//初始化Spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//获得Mapper的代理对象
		TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
		//设置分页条件
		PageHelper.startPage(1, 10);
		//设置查询参数
		TbItemExample example = new TbItemExample();
		//执行查询
		List<TbItem> list = itemMapper.selectByExample(example);
		//变量查询结果
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle());
		}
		
		//获取查询的总记录数等信息还需要用到pageinfo对象
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
	}
}
