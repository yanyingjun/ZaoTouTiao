package com.zhishun.zaotoutiao.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 统一JSON返回信息工具类
 *
 * @author 朱思雷
 * @version $Id: JsonResultUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月07日 21:01
 */
public class JsonResultUtil {

    /**
     * 成功.
     *
     * @param msg 成功提示
     * @return
     */
    public static JSONObject success(String msg) {
        JSONObject json = new JSONObject();
        json.put("flag", true);
        json.put("errorCode", "000");
        json.put("msg", msg);
        return json;
    }

    public static JSONObject error(String msg) {
        JSONObject json = new JSONObject();
        json.put("flag", false);
        json.put("errorCode", "001");
        json.put("msg", msg);
        return json;
    }

    public static JSONObject error(int errorCode, String msg) {
        JSONObject json = new JSONObject();
        json.put("flag", false);
        json.put("errorCode", errorCode);
        json.put("msg", msg);
        return json;
    }

    public static JSONObject error(String errorCode,String msg) {
        JSONObject json = new JSONObject();
        json.put("flag", false);
        json.put("errorCode", errorCode);
        json.put("msg", msg);
        return json;
    }


    /**
     * 前端不分页数据
     *
     * @param list
     * @return
     */
    public static JSONObject frontendList(List list) {
        JSONObject json = new JSONObject();
        json.put("flag", true);
        json.put("msg", "");
        json.put("datas", list);
        return json;
    }

    /**
     * 单个数据返回 带状态.
     *
     * @param object
     * @return
     * @author Chrx
     */
    public static JSONObject singleResult(Object object) {
        JSONObject json = new JSONObject();
        json.put("flag", true);
        json.put("msg", "获取成功");
        json.put("data", object);
        return json;
    }
}
