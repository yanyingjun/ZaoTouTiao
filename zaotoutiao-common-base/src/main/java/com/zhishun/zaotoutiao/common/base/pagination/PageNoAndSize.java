/**
 * @company 杭州智顺文化传播有限公司
 * @copyright Copyright (c) 2017 - 2018
 */


package com.zhishun.zaotoutiao.common.base.pagination;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author BugMan
 * @version $Id: pageNoAndSize, v0.1 2018年03月24日 18:27BugMan Exp $
 */

public class PageNoAndSize {
    public static Map<String,Integer> getNum(int pageNo, int pageSize){
        if (pageNo<0){
            pageNo = 0;
        }
        if(pageSize < 0){
            pageSize =0;
        }
        int startNo = pageNo * pageSize;
        int endNo = (pageNo+1) * pageSize;
        Map<String,Integer> map = Maps.newHashMap();
        map.put("startNo",startNo);
        map.put("endNo",endNo);
        return map;
    }
}
