package zany.util;

import com.google.gson.GsonBuilder;

/**
**/

/**
 * Json Util
 * com.google.code.gson 필요
 * 
 * @author 윤준호
 * @version
 * <pre>
 *  160607 | 윤준호 | 최초작성
 * </pre>
 * @see
 * <dependency>
 * <groupId>com.google.code.gson</groupId>
 * <artifactId>gson</artifactId>
 * <version>2.6.2</version>
 * </dependency>
 * 
 */
public class JsonUtil {

//    static public String prettyPrint(JsonObject json){
//        String result = new GsonBuilder().setPrettyPrinting().create().toJson(json);
//        return result;
//    }
    static public String prettyPrint(Object obj){
        String result = new GsonBuilder().setPrettyPrinting().create().toJson(obj);
        return result;
    }
    static public String convertJson(Object obj){
        String result = new GsonBuilder().create().toJson(obj);
        return result;
    }
    
}
