package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.pojo.TbUser;
import com.offcn.sellergoods.service.MyUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myUser")
public class MyUserController {

    @Reference
    private MyUserService myUserService;

    /**
     * 查询+分页
     * @param user
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbUser user, int  page, int  rows){
        return myUserService.findPage(user, page, rows);
    }
}
