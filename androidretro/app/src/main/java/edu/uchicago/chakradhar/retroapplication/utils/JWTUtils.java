package edu.uchicago.chakradhar.retroapplication.utils;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

//https://stackoverflow.com/questions/37695877/how-can-i-decode-jwt-token-in-android
//usage -> JWTUtils.decoded("eyJ...");
public class JWTUtils {

    //the code left of the period (.) contains the header
    public static String decodedHeader(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            return getJson(split[0]);
        } catch (UnsupportedEncodingException e) {
            Log.e("JWTUtils", e.toString());
            return  null;
        }
    }

    //the code right of the period (.) contains the body
    public static String decodedBody(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            return getJson(split[1]);
        } catch (UnsupportedEncodingException e) {
            Log.e("JWTUtils", e.toString());
            return  null;
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
