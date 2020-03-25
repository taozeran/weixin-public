package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by taozeran on 2020/3/25/12:32
 */
public class SignatureUtils {
    public static boolean checkSign(String token, String timestamp, String nonce, String signature) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = StringUtils.sortAndMerge(token, timestamp, nonce);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] digest = sha.digest(s.getBytes("UTF-8"));
        String res = StringUtils.bytes2hex(digest);
        return res.equals(signature);
    }

    public static void main(String[] args) throws Exception {
        boolean b = checkSign("abcasd", "1585116834", "1825328339", "031dfa12fe2e25832b460f5f9917429ec41cd5d2");
        System.out.println(b);
    }
}
