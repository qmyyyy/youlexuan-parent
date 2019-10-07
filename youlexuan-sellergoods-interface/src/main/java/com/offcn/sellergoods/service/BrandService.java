package com.offcn.sellergoods.service;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 分页查询所有品牌信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum, int pageSize);

    /**
     * 查询分页
     * @param pageNum 当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    public PageResult findPage(TbBrand brand, int pageNum,int pageSize);

    /**
     * 增加品牌
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 查询当前ID品牌的信息
     * @return
     */
    public TbBrand findOne(long id);

    /**
     * 修改当时品牌的信息
     */
    public void update(TbBrand brand);

    /**
     * 删除品牌信息
     * @param ids 品牌ID数组
     */
    public void delete(Long [] ids);

    /**
     * 品牌下拉框数据
     */
    List<Map> selectOptionList();
}
