package com.Asana.utilities;

import java.util.HashMap;
import java.util.Map;

public class Dummy {

    private static Map<String, Object> myDummy = new HashMap<>();

    public static <T> T getDummy(String key) {
     return   (T) myDummy.get(key);
    }

    public static <T> void setDummy(String key, T value) {
        myDummy.put(key, value);
    }


}
