package com.zhishun.zaotoutiao.common.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * MAP工具类
 */
public class MapUtil {

    public static MapUtil getInit() {
        return new MapUtil();
    }

    /**
     * 比较器类
     */
    private static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }

    /**
     * 比较器类
     */
    private static class MapValueComparator implements Comparator<Map.Entry<String, Object>> {
        @Override
        public int compare(Map.Entry<String, Object> me1, Map.Entry<String, Object> me2) {
            return me1.getValue().toString().compareTo(me2.getValue().toString());
        }
    }

    /**
     * 使用 Map按key进行排序
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return Maps.newHashMap();
        }
        Map<String, Object> sortMap = new TreeMap(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 使用 Map按value进行排序
     */
    public static Map<String, Object> sortMapByValue(Map<String, Object> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return Maps.newHashMap();
        }
        Map<String, Object> sortedMap = new LinkedHashMap();
        List<Map.Entry<String, Object>> entryList = new ArrayList(oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Object>> iter = entryList.iterator();
        Map.Entry<String, Object> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }

    /**
     * 集合转字符串，按key字母升序排序，“&”拼接一起<br>
     * 除了sign参数
     *
     * @return {b:test, a:1}  -->  a=1&b=test
     */
    public static String mapToStringByKey(Map<String, Object> map) {
        String params = "";
        //按Key进行排序
        Map<String, Object> resultMap = sortMapByKey(map);

        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            params += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (params.length() > 1) {
            params = params.substring(0, params.length() - 1);
        }
        return params;
    }

    /**
     * 集合转字符串，按Value字母升序排序，“&”拼接一起<br>
     * 除了sign参数
     * ps：value值不能为null
     *
     * @return {b:a, a:u, type:b}  -->  b=a&type=b&a=u
     */
    public static String mapToStringByValue(Map<String, Object> map) {
        String params = "";
        //按Key进行排序
        Map<String, Object> resultMap = sortMapByValue(map);
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            params += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (params.length() > 1) {
            params = params.substring(0, params.length() - 1);
        }
        return params;
    }


    /**
     * 集合转字符串，按key字母升序排序<br>
     *
     * @return {b:test, a:1}  -->  a1btest
     */
    public String getStringByKey(Map<String, Object> map) {
        String params = "";
        //按Key进行排序
        Map<String, Object> resultMap = sortMapByKey(map);

        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
        	switch(entry.getKey()){
        		case "ThirdPartyBizInfo":
        		break;
        		case "UserInfo":
        		break;
        		case "OrderInfo":
        		break;
        		case "ThirdPartyIdList":
        		break;
        		default:
        		params += entry.getKey() + entry.getValue();
        		break;
        	}
        }
        return params;
    }

    /**
     * 将字符串转换为map
     *
     * @param src  需要转换的字符串
     * @param separator  分隔符
     * @return
     */
    public static Map<String, String> stringToMap(String src, String separator) {
        if (StringUtils.isBlank(src) || StringUtils.isBlank(separator)) {
            return null;
        }
        String[] array = StringUtils.split(src, separator);
        Map<String, String> map = Maps.newHashMap();
        for (String ele : array){
            map.put(ele, ele);
        }
        return map;
    }
}
