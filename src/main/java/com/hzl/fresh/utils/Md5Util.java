package com.hzl.fresh.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Util {
    public Md5Util() {
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return (new BigInteger(1, md.digest())).toString(16);
        } catch (Exception var2) {
            return "";
        }
    }
}
