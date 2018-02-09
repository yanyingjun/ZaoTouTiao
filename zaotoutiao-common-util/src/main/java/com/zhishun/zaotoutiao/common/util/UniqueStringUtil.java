package com.zhishun.zaotoutiao.common.util;

import java.util.Random;

/**
 * 唯一字符串生成类
 *
 * @author 朱思雷
 * @version $Id: UniqueStringUtil, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2017年09月14日 19:42
 */
public class UniqueStringUtil {

    public UniqueStringUtil() {
    }

    /**
     * 定义自身对象
     */
    private static UniqueStringUtil instance;

    /**
     * 实例化
     *
     * @return 字符操作实例
     */
    public static UniqueStringUtil getInstance() {

        if (null == instance) {
            instance = new UniqueStringUtil();
        }

        return instance;
    }

    /**
     * 短链接操作码 - 数字、大小写字母 - 常量
     */
    private static final String RULE_CHARACTER = "9876543210abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 短链接操作码长度 - 常量
     */
    private final static int LENGTH = 15;

    /**
     * 随机产生指定位数的随机数 - 规则：有数字、大写字母及小写字母构成.
     *
     * @return 随机字符串
     */
    public String getRandomString() {

        int length = LENGTH;

        // 1、获取可用的字符串常量
        String charRule = RULE_CHARACTER;

        // 2、接收随机结果值
        StringBuilder result = new StringBuilder();

        // 3、获取规则字符串长度
        int len = charRule.length();

        // 4、定义随机对象Random
        Random random = new Random();

        // 5、循环处理
        for (int i = 0; i < length; i++) {
            char asc = charRule.charAt(random.nextInt(len));
            result.append(asc);
        }

        // 6、通过获取的随机字符串得到最后一位
        result.append(getLastChar(result.toString(), length));

        return result.toString();
    }

    /**
     * 根据前五位得到第6位字符.
     *
     * @param code   编码字符串
     * @param length 字符串长度
     * @return 第6位字符
     */
    private static String getLastChar(String code, int length) {

        // 1、得到前四位的ASCLL值之和
        int sum = 0;
        for (int i = 0; i < length - 1; i++) {
            // 2计算前四位的ASCLL值之和
            sum += (int) code.charAt(i);
        }

        // 3、得到第五位的ASCLL的值
        int beforeAscll = (int) code.charAt(length - 1);

        // 4、计算最后一位的ASCLL值
        int lastAscll = (sum + beforeAscll * 3) % 26 + 65;

        // 5、将最后一位ASCLL值转换为字符
        return (char) lastAscll + "";
    }
}
