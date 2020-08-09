package com.boss.bes.paper.utils;


import boss.bes.log.exception.type.AppException;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * description:进行POJO之间的转换
 * @author:zyz
 * time:2020/7/5-15:55
 */
public final class BeanUtil {
    private BeanUtil() {

    }

    /**
     * 复制名称相同类型相同的字段数据
     *
     * @param srcObj 原对象
     * @param target 目标类型
     * @param <T>    目标类型
     * @return target类型对象
     */
    public static <T> T copyData(Object srcObj, Class<T> target) {
        T targetObj = null;
        try {
            targetObj = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new AppException("-1", e.getMessage(), e);
        }
        copyData(srcObj, targetObj);
        return targetObj;
    }

    /***
     * 判断属性名称是否相等
     * @param fields
     * @param fieldName
     * @return
     */
    private static Field match(List<Field> fields, String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    /***
     * 复制对象属性名称相同的
     * @param srcObj
     * @param targetObj
     * @param <T>
     */
    public static <T> void copyData(Object srcObj, T targetObj) {
        Class<?> srcClass = srcObj.getClass();
        List<Field> srcFields = getAllField(srcClass);
        List<Field> targetFields = getAllField(targetObj.getClass());
        for (Field f : targetFields) {
            Field match = match(srcFields, f.getName());
            if (match != null && f.getType().equals(match.getType()) && !Modifier.isFinal(f.getModifiers())) {
                f.setAccessible(true);
                match.setAccessible(true);
                try {
                    f.set(targetObj, match.get(srcObj));
                } catch (IllegalAccessException e) {
                    throw new AppException("-1", e.getMessage(), e);
                }
            }
        }
    }

    /***
     * 获取所有对象的属性
     * @param cls
     * @return
     */
    private static List<Field> getAllField(Class<?> cls) {
        List<Field> list = new ArrayList<>();
        while (!cls.equals(Object.class)) {
            Field[] declaredFields = cls.getDeclaredFields();
            Collections.addAll(list, declaredFields);
            cls = cls.getSuperclass();
        }
        return list;
    }


    /**
     * 用来进行集合之间的转换
     *
     * @param inputs
     * @param clz
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> List<T> convertListToList(List<E> inputs, Class<T> clz) {
        List<T> output = new ArrayList<>();
        if (!inputs.isEmpty()) {
            for (E input : inputs) {
                output.add(copyData(input, clz));
            }
        }
        return output;
    }

    /**
     * 分页信息转换
     *
     * @param inputs
     * @param clz
     * @param <E>
     * @param <T>
     * @return
     */
    public static <E, T> PageInfo<T> convertPageInfo(PageInfo<E> inputs, Class<T> clz) {
        PageInfo<T> pageInfo = new PageInfo<>();
        copyData(inputs, pageInfo);

        pageInfo.setList(convertListToList(inputs.getList(), clz));
        return pageInfo;
    }
}
