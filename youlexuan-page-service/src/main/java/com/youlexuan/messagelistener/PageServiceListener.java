package com.youlexuan.messagelistener;

import com.offcn.page.service.ItemPageService;
import com.offcn.util.SerializeUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

public class PageServiceListener implements MessageListener {

    @Autowired
    ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {

        byte[] body = message.getBody();
        Long[] ids = (Long[])SerializeUtils.unserialize(body);
        for(Long id :ids){
            itemPageService.genItemHtml(id);
        }

    }
}
