package com.longchain.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class StringUtils {
    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuilder buffer = new StringBuilder();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String hex2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        hexStr = hexStr.substring(2);
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.substring(2);
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static Long hex2Long(String hexStr) {
        hexStr = hexStr.substring(2);
        return Long.parseLong(hexStr, 16);
    }

    public static String getSHA256StrJava(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static String str2Hex(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static Boolean isNotBlank(String str) {
        return !(null == str || str.length() == 0);
    }

    public static String long2Hex(Long num) {
        char[] chs = new char[64];//定义容器，存储的是字符，长度为8.一个整数最多8个16进制数
        int index = chs.length - 1;
        for (int i = 0; i < 64; i++) {
            long temp = num & 15;

            if (temp > 9) {
                chs[index] = ((char) (temp - 10 + 'A'));
            } else {
                chs[index] = ((char) (temp + '0'));
            }

            index--;
            num = num >>> 4;
        }
        return new String(chs);
    }

    public static String prefixTo32(String s){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 64-s.length();i++){
            stringBuilder.append("0");
        }
        return stringBuilder.append(s).toString();
    }

    public static String suffixTo32(String s){
        StringBuilder stringBuilder = new StringBuilder(s);
        for (int i = 0; i < 64-s.length();i++){
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
    }
}
