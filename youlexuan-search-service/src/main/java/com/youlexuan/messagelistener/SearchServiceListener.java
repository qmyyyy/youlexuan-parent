package com.youlexuan.messagelistener;

import com.offcn.pojo.TbItem;
import com.offcn.search.service.ItemSearchService;
import com.offcn.util.SerializeUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SearchServiceListener implements MessageListener {

    @Autowired
    ItemSearchService itemSearchService;

    @Override
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        System.out.println("youlexuan-search-service   " + body);

//        解耦
        List<TbItem> itemList =  (List<TbItem>)SerializeUtils.unserialize(body);

        itemSearchService.importItemListToSolr(itemList);

    }
}
