package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {

        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {

        //使用mybatis的分页插件来实现分页
        PageHelper.startPage(pageNum,pageSize);

        Page<TbBrand> page = (Page<TbBrand>)brandMapper.selectByExample(null);

        PageResult pageResult = new PageResult();

        pageResult.setTotal(page.getTotal());//总记录数
        pageResult.setRows(page.getResult());

        return pageResult;
    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        //逆向生成的一个方法xxxExample
        TbBrandExample example=new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();

        //如果查询框内有数据，则按照品牌名称模糊查询或品牌首字母查询
        if (brand != null){
            if(brand.getName() != null && brand.getName().length() > 0){
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if(brand.getFirstChar() != null && brand.getFirstChar().length() > 0){
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        //如果复选框内没有内容，默认查询所有（分页）
        Page<TbBrand> page= (Page<TbBrand>)brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        brandMapper.insert(brand);
    }

    @Override
    public TbBrand findOne(long id) {
        brandMapper.selectByPrimaryKey(id);
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public void delete(Long[] ids) {
        for (long id:ids) {
            brandMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }
}
