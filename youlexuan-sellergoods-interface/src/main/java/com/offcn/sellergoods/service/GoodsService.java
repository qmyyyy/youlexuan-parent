package com.offcn.sellergoods.service;
import java.util.List;
import com.offcn.entity.PageResult;
import com.offcn.group.Goods;
import com.offcn.pojo.TbGoods;


/**
 * 商品服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbGoods> findAll();


	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 * 增加
	 */
	public void add(TbGoods goods);


	/**
	 * 修改
	 */
	public void update(TbGoods goods);


	/**
	 * 根据ID获取实体
	 *
	 */
	public TbGoods findOne(Long id);


	/**
	 * 批量删除
	 * @param
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 *
	 */
	public PageResult findPage(TbGoods goods, int pageNum, int pageSize);

	/**
	 * 增加，传递组合参数
	 */
	public void add(Goods goods);

	/**
	 * 批量修改状态
	 *
	 */
	public void updateStatus(Long []ids,String status);

	public void updateMarketable(Long[] ids, String status);

}
