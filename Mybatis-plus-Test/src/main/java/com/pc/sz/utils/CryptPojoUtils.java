package com.pc.sz.utils;

import com.*.annotation.DecryptField;
import com.*.annotation.EncryptField;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @decription CryptPojoUtils
 * <p>对象加解密工具
 * 通过反射，对参数对象中包含指定注解的字段进行加解密。
 * 调用<tt>encrypt(T t)</tt>方法实现加密，返回加密后的对象；
 * 调用<tt>decrypt(T t)</tt>实现解密，返回解密后的对象；
 * <tt>encrypt</tt>对注解{@link EncryptField}字段有效；
 * <tt>decrypt</tt>对注解{@link DecryptField}字段有效。</p>
 * @author cheng
 * @date 2021/08/17 13:36
 */
public class CryptPojoUtils {
    /**
     * 对象t注解字段加密
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T encrypt(T t) {
        if (isEncryptAndDecrypt(t)) {
            Field[] declaredFields = t.getClass().getDeclaredFields();
            try {
                if (declaredFields != null && declaredFields.length > 0) {
                    for (Field field : declaredFields) {
                        if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                            field.setAccessible(true);
                            String fieldValue = (String) field.get(t);
                            if (StringUtils.isNotEmpty(fieldValue)) {
                                field.set(t, AESUtil.encrypt(fieldValue));
                            }
                            field.setAccessible(false);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    /**
     * 对含注解字段加密
     * @param t
     * @param <T>
     */
    public static <T> void encryptField(T t) {
        Map<String,Object> map = (Map<String, Object>) t;
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(EncryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(t);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, AESUtil.encrypt(fieldValue));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密单独的字符串
     *
     * @param @param  t
     * @param @return
     * @return T
     * @throws
     */
    public static <T> T encryptStr(T t) {
        if (t instanceof String) {
            t = (T) AESUtil.encrypt((String) t);
        }
        return t;
    }

    /**
     * 对含注解字段解密
     *
     * @param t
     * @param <T>
     */
    public static <T> T decrypt(T t) {
        if (isEncryptAndDecrypt(t)) {
            Field[] declaredFields = t.getClass().getDeclaredFields();
            try {
                if (declaredFields != null && declaredFields.length > 0) {
                    for (Field field : declaredFields) {
                        if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                            field.setAccessible(true);
                            String fieldValue = (String) field.get(t);
                            if (StringUtils.isNotEmpty(fieldValue)) {
                                field.set(t, AESUtil.decrypt(fieldValue));
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return t;
    }

    /**
     * 对含注解字段解密
     * @param t
     * @param <T>
     */
    public static <T> void decryptField(T t) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(t);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            field.set(t, AESUtil.decrypt(fieldValue));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // return t;
    }

    public static Object getDecryptMapValue(Object returnValue, String[] fields){
        return null;
    }

    public static Object getEncryptMapValue(Object parameter, String[] fields) {
        Map<String,Object> map = null;
        try {
            map = (Map<String, Object>) parameter;
        } catch (Exception e) {
            return parameter;
        }

        for (String field : fields) {
            if (null == map.get(field)) continue;
            if (map.get(field) instanceof String) {
                String value = String.valueOf(map.get(field));
                if (Strings.EMPTY.equals(value)) continue;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (value.equals(entry.getValue())) {
                        map.put(entry.getKey(), AESUtil.encrypt(value));
                    }
                }
            }
        }
        return map;
    }

    /**
     * 判断是否需要加密解密的类
     *
     * @param @param  t
     * @param @return
     * @return Boolean
     * @throws
     */
    public static <T> Boolean isEncryptAndDecrypt(T t) {
        return true;
    }


    /**
     * 隐藏号码中间4位
     * @param t
     * @param <T>
     */
    public static <T> void hidePhone(T t) {
        Field[] declaredFields = t.getClass().getDeclaredFields();
        try {
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.isAnnotationPresent(DecryptField.class) && field.getType().toString().endsWith("String")) {
                        field.setAccessible(true);
                        String fieldValue = (String)field.get(t);
                        if(StringUtils.isNotEmpty(fieldValue)) {
                            // 暂时与解密注解共用一个注解，该注解隐藏手机号中间四位
                            field.set(t, StringUtils.overlay(fieldValue, "****", 3, 7));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
