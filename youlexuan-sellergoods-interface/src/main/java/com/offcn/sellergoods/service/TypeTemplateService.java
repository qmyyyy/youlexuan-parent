package com.offcn.sellergoods.service;
import java.util.List;
import java.util.Map;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbTypeTemplate;

/**
 * 类型模板服务层接口
 * @author Administrator
 *
 */
public interface TypeTemplateService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbTypeTemplate> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbTypeTemplate type_template);
	
	
	/**
	 * 修改
	 */
	public void update(TbTypeTemplate type_template);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbTypeTemplate findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbTypeTemplate type_template, int pageNum, int pageSize);

	/**
	 * 模板下拉框数据
	 */
	List<Map> selectOptionList();

	/**
	 * 返回指定模板id的规格列表，包含规格选项（根据模板id查询规格列表spec_ids）
	 * @return
	 */
	public List<Map> findSpecList(Long id);
	
}