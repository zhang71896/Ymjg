package com.yrj520.pfapp.ymjg.UI.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
    /**
     * 判断http返回是否成功
     *
     * @param status 返回状态
     * @return true or false
     */
    public static boolean isResponseOk(int status) {
        return status == 0 || status == 1;
    }

    /**
     * Description:判断token是否失效
     *
     * @param status 返回状态
     * @return true or false
     */
    public static boolean isInvalidToken(int status) {
        return status == 100 || status == 101 || status == 102;
    }


    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 验证邮箱
     *
     * @param email 邮箱
     * @return true or false
     */
    public static boolean checkEmail(String email) {
        boolean flag;
        try {
            String check = "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";

            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码
     *
     * @param mobileNumber 手机号
     * @return true or false
     */
    public static boolean checkMobileNumber(String mobileNumber) {
        boolean flag;
        try {
            Pattern regex = Pattern.compile("^1[34578]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手用户密码(密码必须由数字、字母两种组成)
     *
     * @param pwd 密码
     * @return true or false
     */
    public static boolean checkPwd(String pwd) {
        boolean flag;
        try {
            Pattern regex = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
            Matcher matcher = regex.matcher(pwd);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手用户密码(密码包含数字、字母或下划线)
     *
     * @param pwd 密码
     * @return true or false
     */
    public static boolean checkPwd1(String pwd) {
        boolean flag;
        try {
            Pattern regex = Pattern.compile("^[0-9A-Za-z_＿]{8,16}$");
            Matcher matcher = regex.matcher(pwd);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 保留两位小数
     *
     * @param str 小数
     * @return string
     */
    public static String doubleforfloat(Double str) {
        BigDecimal bg = new BigDecimal(str);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        return df.format(f1);
    }

    /**
     * 保留一位小数
     *
     * @param str 小数
     * @return string
     */
    public static String doubleforfloat01(Double str) {
        BigDecimal bg = new BigDecimal(str);
        double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.0");
        return df.format(f1);
    }

    /**
     * 电话号码4-7位数变*
     *
     * @param phone 电话
     * @return string
     */
    public static String ChangePhone(String phone) {
        // 截取第1位到第3位
        String s1 = phone.substring(0, 3);

        // 截取第8到11位
        String s3 = phone.substring(phone.length() - 4, phone.length());

        // 定义第4到7位数为星星
        String s2 = "****";
        StringBuffer sb = new StringBuffer();
        sb.append(s1).append(s2).append(s3);

        return sb.toString();
    }

    /**
     * 邮箱中间数字变*
     *
     * @param email
     */
    public static String changeEmail(String email) {
        String s1[] = email.split("@");
        String s2 = s1[0].substring(0, 1);
        String s3 = s1[0].substring(s1[0].length() - 2, s1[0].length());
        String s4 = "****";
        StringBuffer bf = new StringBuffer();
        bf.append(s2).append(s4).append(s3).append("@").append(s1[1]);
        return bf.toString();
    }

    /**
     * 判断字符串中是否仅包含英文字母、数字和汉字
     *
     * @param str 字符串参数
     * @return true or false
     */
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";
        return str.matches(regex);
    }

    /**
     * 判断字符串中是否仅包含英文字母、数字、汉字和下划线(中文和英文下划线)
     *
     * @param str 字符串
     * @return true or false
     */
    public static boolean isLetterDigitOrChinese1(String str) {
        String regex = "^[\\u4e00-\\u9fa5\\w＿]+$";
        return str.matches(regex);
    }


    /**
     * 把String数组装换成字符串以/隔开
     */
    public static String getString(String[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String anArr : arr) {
            stringBuilder.append(anArr).append("/");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 把int类型的数组转换成以/隔开
     */
    public static String getIntForString(Integer[] str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer aStr : str) {
            stringBuilder.append(aStr).append("/");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
