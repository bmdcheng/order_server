package net.xdclass.order_server.util;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

/**
 * json 工具类
 */
public class JsonUtil {

    private JsonUtil(){};

    public static JSONObject convStrToJson(String str){
        if(StringUtils.isEmpty(str))
            return null;

        return JSONObject.parseObject(str);
    }
}
