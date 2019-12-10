package com.mbfw.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class JSONUtil {  
    private static ObjectMapper mapper = new ObjectMapper();  
      
    /** 
     * 将实体对象转换成JSON格式的字符串 
     * @param obj 
     * @return 
     */  
    public static String toJson(Object obj){  
        String json = "";  
        try {  
            StringWriter writer = new StringWriter();  
            JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(writer);  
            mapper.writeValue(generator, obj);  
            json = writer.toString();  
            generator.close();  
            writer.close();           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
        return json;  
    }  
      
    /** 
     * JSON格式的字符串转成实体对象 
     * @param json 
     * @param valueType 
     * @return 
     */  
    public static <T> T fromJson(String json, Class<T> valueType){  
        try {  
            return null == json ? null : mapper.readValue(json, valueType);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /***
     * json 转 List<T>
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
       
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
	    
   public static JSONObject jsonToMap(String jsonData) {
	    	
	    	if (jsonData == null || "".equals(jsonData)) 
	    		   return new JSONObject(); 
	    		  
	    		    
	    		  return JSONObject.parseObject(jsonData);  
	    }
 
	    
}  