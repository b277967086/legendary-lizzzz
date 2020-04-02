package com.good.diaodiaode.tebiediao.utils;

import com.good.diaodiaode.tebiediao.utils.MD5Helper;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ex-lizheng102 on 2017-03-01.
 */

public class SignUtils {

    public SignUtils() {
    }

    /**
     * @param headers        http头
     * @param businessParams 业务参数
     */
    public static String getApiSign(Map<String, String> headers, Map<String, String> businessParams) {

        String apiSing = "";
        TreeMap<String, String> params = new TreeMap<>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                } else {
                    return String.valueOf(o1).compareTo(String.valueOf(o2));
                }
            }
        });
        params.putAll(headers);
        if (null != businessParams) {
            params.putAll(businessParams);
        }

        String charset = "utf-8";
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] array;
        try {
            byteStream.write("byteStream".getBytes(charset));
            for (String key : params.keySet()) {
                byteStream.write(key.getBytes(charset));
                if (null != params.get(key)) {
                    byteStream.write(params.get(key).getBytes(charset));
                }
            }
            byteStream.write("byteStream".getBytes(charset));
            array = byteStream.toByteArray();
            byteStream.close();
//            String str = new String(array);
//            System.out.print("-----------------------"+str);
            apiSing = MD5Helper.md5Hex(array);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiSing;
    }

    /**
     * @param headers        http头
     * @param businessParams 业务参数
     */
    public static String getApiSign2(Map<String, String> headers, Map<String, String> businessParams) {

        String apiSing = "";
        TreeMap<String, String> params = new TreeMap<>(new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                } else {
                    return String.valueOf(o1).compareTo(String.valueOf(o2));
                }
            }
        });
        params.putAll(headers);
        if (null != businessParams) {
            params.putAll(businessParams);
        }

        String charset = "utf-8";
        byte[] array;
        StringBuffer sb =  new StringBuffer();
        try {
            sb.append("byteStream");
            for (String key : params.keySet()) {
                sb.append(key);
                if (null != params.get(key)) {
                    sb.append(params.get(key));
                }
            }
            sb.append("byteStream");
            array = sb.toString().getBytes(charset);
            apiSing = MD5Helper.md5Hex(array);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiSing;
    }

}
