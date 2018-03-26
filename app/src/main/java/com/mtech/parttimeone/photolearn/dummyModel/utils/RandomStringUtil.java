package com.mtech.parttimeone.photolearn.dummyModel.utils;

/**
 * @author Leon
 * @date 26/3/18
 */

import java.util.UUID;


public class RandomStringUtil {

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }

}
