package com.example.practicespring.util;

import java.util.Base64;

public class Base64Util
{

    // Encode byte[] to Base64 String
    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    // Decode Base64 String to byte[]
    public static byte[] decode(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}


//
//The utility (util) package in Java typically contains helper classes and methods that provide
//        general-purpose functionality. These utilities can be used across different parts of an
//application to perform common tasks. In the context of the code you’re working with, the Base64Util class
//is an example of a utility class.