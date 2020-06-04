package com.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public class JsonUtil {

    /**
     * 将JAVA对象转换成JSON对象
     * @return String
     */
    public static String ObjToJson(Object obj){
        JSONObject json = JSONObject.fromObject(obj);//将java对象转换json对象
        return json.toString();//将json对象转换成String
    }

    /**
     * 将JAVA对象转换成JSON对象
     * @return String
     */
    public static String arrToJson(Object obj){
        JSONArray json = JSONArray.fromObject(obj);//将java对象转换json对象
        return json.toString();//将json对象转换成String
    }

    /**
     * 将JAVA集合对象转换为JSON对象
     * @return String
     */
    public static String listToJson(List<Object> list){
        JSONArray jsonArr= JSONArray.fromObject(list);//将java对象转换json对象
        return jsonArr.toString(); //返回json字符串
    }
}
