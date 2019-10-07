package com.offcn.sellergoods.controller;
import java.util.List;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.page.service.ItemPageService;
import com.offcn.pojo.TbItem;
import com.offcn.search.service.ItemSearchService;
import com.offcn.sellergoods.service.ItemService;
import com.offcn.util.SerializeUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.pojo.TbGoods;
import com.offcn.sellergoods.service.GoodsService;

/**
 * 商品controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Reference
	private GoodsService goodsService;

	@Reference(timeout=40000)
	private ItemPageService itemPageService;

	@Reference(timeout=40000)
	private ItemSearchService searchService;

	@Reference(timeout=40000)
	private ItemService itemService;

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 生成静态页（测试）
	 * @param goodsId
	 */
	@RequestMapping("/genHtml")
	public void genHtml(Long goodsId){
		itemPageService.genItemHtml(goodsId);
	}


	/**
	 * 返回全部列表
	 *
	 */
	@RequestMapping("/findAll")
	public List<TbGoods> findAll(){
		return goodsService.findAll();
	}


	/**
	 * 返回全部列表
	 *
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return goodsService.findPage(page, rows);
	}

	/**
	 * 增加

	 */


	/**
	 * 修改
	 *
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbGoods goods){
		try {
			goodsService.update(goods);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}

	/**
	 * 获取实体
	 *
	 */
	@RequestMapping("/findOne")
	public TbGoods findOne(Long id){
		return goodsService.findOne(id);
	}

	/**
	 * 批量删除
	 *
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			goodsService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}

	/**
	 * 查询+分页
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbGoods goods, int page, int rows  ){
		return goodsService.findPage(goods, page, rows);
	}

	/**
	 * 更新状态
	 *
	 */
	@RequestMapping("/updateStatus")
	public Result updateStatus(Long[] ids, String status) {
		try {
			goodsService.updateStatus(ids,status);

			//生成静态页
			//什么情况下需要生成静态页？ 商品审核通过
			/*if(status.equals("1")){

				//1.将审核通过的skulist 导入solr
				//查询spu对应的sku列表
				for(Long id : ids){
					List<TbItem> tbItems = itemService.findItemList(id);
					//调用搜索服务 importItemListToSolr方法，将sku列表导入到solr中
					searchService.importItemListToSolr(tbItems);
				}

				//2.生成静态页
				for(Long id : ids){
					itemPageService.genItemHtml(id);
				}
			}*/

			//生成静态页
			//什么情况下需要生成静态页？ 商品审核通过
			if(status.equals("1")){
				for(Long id : ids){
					List<TbItem> tbItems = itemService.findItemList(id);
					// searchService.importItemListToSolr(tbItems);
					amqpTemplate.convertAndSend("searchQueueKey",SerializeUtils.serialize(tbItems));/* ids作为消息？？可以 */
				}

				//2.生成静态页
				amqpTemplate.convertAndSend("pageQueueKey",SerializeUtils.serialize(ids));/*ids作为消息*/
			}

			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	}

}
