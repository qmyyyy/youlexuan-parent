package com.offcn.search.service;

import com.offcn.pojo.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemSearchService {

    /**
     * 搜索
     * @param
     * @return
     */
    public Map<String,Object> search(Map searchMap);

    public void importItemListToSolr(List<TbItem> itemList);

}
