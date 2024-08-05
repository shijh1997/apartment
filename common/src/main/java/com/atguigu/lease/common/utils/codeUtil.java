package com.atguigu.lease.common.utils;

import java.util.Random;

public class codeUtil {
    public static String getRandomCode(Integer length){
        StringBuilder Builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(10);
            Builder.append(num);
        }

        return Builder.toString();
    }
}
