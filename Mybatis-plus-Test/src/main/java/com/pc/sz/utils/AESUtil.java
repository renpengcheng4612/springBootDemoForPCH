package com.pc.sz.utils;

import com.*.bean.enums.AesEnum;
import com.pc.sz.enums.AesEnum;
import org.thymeleaf.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description 修改工具类
 * 新增前端交互加密解密AES工具类(RandomStringUtils 随机生成key和偏移量)
 * @Date 2019-12-24
 */
public class AESUtil {
    private static final String AES_KEY = "jafndsafa";
    private static final String AES_IV = "411231411313";

    public static String encrypt(String value, AesEnum aesEnum) {
        try {
            return assemble(value, aesEnum.getKey(), aesEnum.getIv());
        } catch (Exception e) {
            return null;
        }
    }

    public static String encrypt(String value) {
        try {
            return assemble(value, AES_KEY, AES_IV);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String secValue, AesEnum aesEnum) {
        if (StringUtils.isEmpty(secValue)) return null;
        try {
            return disassemble(secValue, aesEnum.getKey(), aesEnum.getIv());
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String secValue) {
        if (StringUtils.isEmpty(secValue)) return null;
        try {
            return disassemble(secValue, AES_KEY, AES_IV);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encrypt(String value, String key, String iv) {
        try {
            return assemble(value, key, iv);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String secValue, String key, String iv) {
        try {
            return disassemble(secValue, key, iv);
        } catch (Exception e) {
            return null;
        }
    }

    private static String assemble(String sSrc, String sKey, String ivStr) throws Exception {
        if (sKey == null) {
            return null;
        }
        if (sKey.length() != 16) {
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        if (ivStr != null) {
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        }
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return byte2hex(encrypted).toLowerCase();
    }

    private static String disassemble(String sSrc, String sKey, String ivStr) throws Exception {
        try {
            if (sKey == null) {
                return null;
            }
            if (sKey.length() != 16) {
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            if (ivStr != null) {
                IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
            }
            byte[] encrypted1 = hex2byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

}