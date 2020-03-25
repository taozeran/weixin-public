package com.example.demo.utils;

import java.util.Arrays;

/**
 * Created by taozeran on 2020/3/25/11:54
 */
public class StringUtils {
    public static String sortAndMerge(String... args) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(args);
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
        }
        return sb.toString();
    }

    public static String bytes2hex(byte[] arr) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            byte b = arr[i];
            int l = b & 0xF;
            int h = (b >> 4)&0xF;
            ans.append(Character.forDigit(h,16));
            ans.append(Character.forDigit(h,16));
        }
        return ans.toString();
    }
}
