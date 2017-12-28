package com.web.component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by gaoyang on 17/12/28.
 */
public class Sign {

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     *
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     *
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    public static String jsTicketSign(String jsapi_ticket, String noncestr, String timestamp, String url) {
        String sort = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr +
                "&timestamp=" + timestamp + "&url=" + url;
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(sort.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return tmpStr.toLowerCase();
    }

    public static void main(String[] args) {
        Sign sign=new Sign();
        System.out.println(sign.jsTicketSign("sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg","Wm3WZYTPz0wzccnW","1414587457","http://mp.weixin.qq.com?params=value"));
    }

}
