package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.mapper.TbUserMapper;
import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;
import com.offcn.pojo.TbUserExample;
import com.offcn.sellergoods.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MyUserImpl implements MyUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public PageResult findPage(TbUser user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //逆向生成的一个方法xxxExample
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        //如果查询框内有数据，则按照品牌名称模糊查询或品牌首字母查询
        if (user != null){
            if(user.getUsername() != null && user.getUsername().length() > 0){
                criteria.andUsernameLike("%" + user.getUsername() + "%");
            }
        }
        //如果复选框内没有内容，默认查询所有（分页）
        Page<TbUser> page= (Page<TbUser>)tbUserMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }
}
