package com.zhishun.zaotoutiao.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author WangYaL
 * @dateTime 2017年08月21日 14:55
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    public static BeanUtils instance;

    /**
     * 实例化.
     */
    public static BeanUtils getInstance() {

        if (instance == null) {

            instance = new BeanUtils();
        }

        return instance;
    }

    /**
     * 比较两个对象中属性值是否都相同.
     * 新对象属性如果为null 跳过比较
     *
     * @param oldBean           旧对象
     * @param newBean           新对象
     * @param excludeProperties 要忽略的属性名
     * @return 是否相等
     * @throws NullPointerException 比较的对象不能为null
     */
    public boolean equalTo(Object oldBean, Object newBean, String... excludeProperties) throws NullPointerException {

        if (oldBean == null || newBean == null) {

            throw new NullPointerException("比较的对象不能为NULL");
        }

        //忽略的属性
        List<String> excludeFields = new ArrayList<>(Arrays.asList(excludeProperties));

        //忽略serialVersionUID
        excludeFields.add("serialVersionUID");

        try {
            Class clazz = oldBean.getClass();
            Field[] fields = oldBean.getClass().getDeclaredFields();
            for (Field field : fields) {

                if (excludeFields.contains(field.getName())) {
                    continue;
                }

                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object oVal = getMethod.invoke(oldBean);
                Object nVal = getMethod.invoke(newBean);

                if (nVal == oVal) {
                    continue;
                }
                //其中一个为null
                if (nVal == null || oVal == null) {
                    return false;
                }
                if (!StringUtils.equals(oVal.toString(), nVal.toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "对象比较", e);
        }
        return true;
    }

    /**
     * 两个对象不相等.
     * 新对象属性如果为null 跳过比较
     *
     * @param oldBean           旧对象
     * @param newBean           新对象
     * @param excludeProperties 要忽略的属性名
     * @return 两个对象不相等
     * @throws NullPointerException 比较的对象不能为null
     */
    public boolean notEqualTo(Object oldBean, Object newBean, String... excludeProperties) {

        return !equalTo(oldBean, newBean, excludeProperties);
    }


    /**
     * 比较两个对象 特定的属性是否相等.
     *
     * @param oldBean           旧对象
     * @param newBean           新对象
     * @param includeProperties 特定需要比较的属性名
     * @return 是否相等
     * @throws NullPointerException 比较的对象不能为null
     */
    public boolean equalToByProperties(Object oldBean, Object newBean, String... includeProperties) throws NullPointerException {

        if (oldBean == null || newBean == null) {

            throw new NullPointerException("比较的对象不能为NULL");
        }

        //需要比较的属性
        List<String> includeFields = new ArrayList<>(Arrays.asList(includeProperties));

        try {
            Class clazz = oldBean.getClass();

            for (String fieldName : includeFields) {

                PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(oldBean);
                Object o2 = getMethod.invoke(newBean);

                if (!StringUtils.equals(o1.toString(), o2.toString())) {
                    return false;
                }
            }
        } catch (Exception e) {
            LoggerUtils.error(LOGGER, "对象比较-特定属性比较", e);
        }

        return true;
    }


    /**
     * bean拷贝.
     *
     * @param fromBean          拷贝源
     * @param toBean            拷贝目标
     * @param excludeProperties 忽略属性
     */
    public void copyTo(Object fromBean, Object toBean, String... excludeProperties) {

        copyProperties(fromBean, toBean, excludeProperties);
    }

    /**
     * bean拷贝 忽略null值.
     *
     * @param fromBean 拷贝源
     * @param toBean   拷贝目标
     */
    public void copyToWithoutNull(Object fromBean, Object toBean) {

        copyProperties(fromBean, toBean, getNullPropertyNames(fromBean).toArray(new String[]{}));
    }

    /**
     * bean拷贝 忽略null值和默认值为0的字段.
     * 默认值为0忽略，目前只支持 int,float,double
     *
     * @param fromBean       拷贝源
     * @param toBean         拷贝目标
     * @param ignoreProperty 忽略字段
     */
    public void copyToWithoutNullAndZero(Object fromBean, Object toBean, String... ignoreProperty) {

        //值为0的属性名
        Set<String> zeroPropertyNames = getZeroValuePropertyNames(fromBean);

        //值为null的属性名
        Set<String> nullPropertyNames = getNullPropertyNames(fromBean);

        //忽略的属性名
        Set<String> excludeProperties = new HashSet<>(Arrays.asList(ignoreProperty));

        excludeProperties.addAll(zeroPropertyNames);
        excludeProperties.addAll(nullPropertyNames);

        copyProperties(fromBean, toBean, excludeProperties.toArray(new String[]{}));
    }


    /**
     * 筛选出值为null的属性名.
     *
     * @param target 目标对象
     * @return 值为null的属性名数组
     */
    private Set<String> getNullPropertyNames(Object target) {

        Set<String> emptyNames = new HashSet<>();

        final BeanWrapper src = new BeanWrapperImpl(target);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {

            Object srcValue = src.getPropertyValue(pd.getName());

            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

        return emptyNames;
    }

    /**
     * 筛选出值为0的属性名.
     * 默认值0筛选，目前只支持 int,float,double三种类型
     *
     * @param target 目标源
     * @return 值为0 的属性名数组
     */
    private static Set<String> getZeroValuePropertyNames(Object target) {
        final BeanWrapper src = new BeanWrapperImpl(target);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {

            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null) {

                if (srcValue.getClass().equals(Integer.class)) {
                    if (0 == (Integer) srcValue){
                        emptyNames.add(pd.getName());
                    }
                    continue;
                }

                if (srcValue.getClass().equals(Float.class)) {
                    if (0.0f == (Float) srcValue) {
                        emptyNames.add(pd.getName());
                    }
                    continue;
                }

                if (srcValue.getClass().equals(Double.class)) {
                    if (0.0d == (Double) srcValue) {
                        emptyNames.add(pd.getName());
                    }
                }
            }
        }

        return emptyNames;
    }
}
