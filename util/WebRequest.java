package zany.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * HTTP Reqeust And Get Response by JSON.
 *
 * @author 윤준호
 * @version
 * <pre>
 *  151119 | 윤준호 | 최초생성
 * </pre>
 */
public class WebRequest{
    
    private static Logger log = LoggerFactory.getLogger(WebRequest.class);
    
    public enum RequestMethod{
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    }
    
    /**------------------------------------------------------------------------------------------------------------------------------------------------------
     * 일반적인(Content-type text/plain) 요청을 위한 영역
     * ------------------------------------------------------------------------------------------------------------------------------------------------------*/
    
    /**
     * URL 로 HTTP GET 요청
     * charSet은 UTF-8
     */
    public static String requestHttp(String url, RequestMethod method) throws MalformedURLException, IOException{
        return requestHttp(url, method, null, "UTF-8");
    }
    
    /**
     * URL로 HTTP 요청 후, 응답을 Json으로 변환하여 리턴
     * charSet은 UTF-8
     */
    public static JsonObject getJson(String url, RequestMethod method) throws MalformedURLException, IOException{
        
        String response     = requestHttp(url, method, null, "UTF-8");
        Gson gson           = new GsonBuilder().setPrettyPrinting().create();
        JsonElement element = gson.fromJson(response, JsonElement.class); 
        JsonObject result   = element.getAsJsonObject();
        
        if(log.isDebugEnabled()){
            log.debug("result:\n{}", gson.toJson(result));
        }
        
        return result;
    }
    
    /**
     * 입력받은 URL 로 HTTP GET 요청 후, JSON 으로 리턴.
     */
    public static String requestHttp(String url, RequestMethod method, String charSet) throws MalformedURLException, IOException{
        return requestHttp(url, method, null, charSet);
    }
    
    /**
     * 인자값과 함께 URL 로 HTTP 요청 후, 응답을 Json으로 변환하여 리턴
     */
    public static String getJson(String url, RequestMethod method, Map<String, String> parameters) throws IOException{
        return requestHttp(url, method, parameters, "UTF-8");
    }
    
    /**
     * 인자값과 함께 URL 로 HTTP 요청
     */
    public static String requestHttp(String url, RequestMethod method, Map<String, String> parameters) throws IOException{
        return requestHttp(url, method, parameters, "UTF-8");
    }

    /**
     * URL 로 HTTP 요청 후 결과 값을 String 으로 리턴.
     */
    public static String requestHttp(String uri, RequestMethod method, Map<String, String> parameters, String charSet) throws IOException{

        //==========================================================================
        // Declaration
        //==========================================================================
        StringBuilder result = new StringBuilder();
        
        //==========================================================================
        // Http Connect Setting
        //==========================================================================
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(method.name());
        connection.setConnectTimeout(5000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        
        
        if( StringUtils.isEmpty(charSet) ){
            charSet = "UTF-8";
        }
        
        //==========================================================================
        // Convert URL Query String
        //==========================================================================
        String queryString = "";
        if ( parameters != null && parameters.size() > 0 ){
            StringBuilder tmp = new StringBuilder();
            boolean first = true;

            for( Map.Entry<String, String> entry : parameters.entrySet() ) {
                
                if(first) {
                    first = false;
                } else {
                    result.append("&");
                }
                    
                tmp.append(entry.getKey());
                tmp.append("=");
                tmp.append(entry.getValue());
            }
            queryString = tmp.toString();
        }
        
        //==========================================================================
        // Http Request
        //==========================================================================
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, charSet));
        writer.write(queryString);

        writer.flush();
        writer.close();
        os.close();
        
        //==========================================================================
        // Read Response
        //==========================================================================
        if ( connection.getResponseCode()  == HttpsURLConnection.HTTP_OK ) {
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line=br.readLine()) != null) {
                result.append(line);
            }
            
        }else{
            throw new IOException("Response:[" + connection.getResponseCode() + "] " + connection.getResponseMessage() );
            
        }

        return result.toString();
        
    }
    
    /**------------------------------------------------------------------------------------------------------------------------------------------------------
     * Content-Type application/json 요청을 위한 영역
     * 인자값으로 Object를 받아 json 으로 변환하여 요청
     * ------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public static JsonObject getJson(String uri, RequestMethod method, Object object) throws IOException{
        return getJson(uri, method, object, null);
    }
    
    public static JsonObject getJson(String uri, RequestMethod method, Object object, String charSet) throws IOException{
        String response = requestHttp(uri, method, object, charSet);
        
        if( StringUtils.isEmpty(response) ) {
            return null;
        }
        
        JsonObject result = new JsonParser().parse(response).getAsJsonObject();
        
        return result;
    }
    
    
    /**
     * URL 로 HTTP 요청 후 결과 값을 String 으로 리턴.
     */
    public static String requestHttp(String uri, RequestMethod method, Object obj) throws IOException{
        return requestHttp(uri,  method,  obj, "UTF-8");
    }
    
    /**
     * URL 로 HTTP 요청 후 결과 값을 String 으로 리턴.
     */
    public static String requestHttp(String uri, RequestMethod method, Object obj, String charSet) throws IOException{

        //==========================================================================
        // Declaration
        //==========================================================================
        StringBuilder result = new StringBuilder();
        
        //==========================================================================
        // Http Connect Setting
        //==========================================================================
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod(method.name());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setConnectTimeout(2000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        
        
        if( StringUtils.isEmpty(charSet) ){
            charSet = "UTF-8";
        }
        
        //==========================================================================
        // Convert Object to 
        //==========================================================================
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        String jsonString = gson.toJson(obj);
        
        //==========================================================================
        // Http Request
        //==========================================================================
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, charSet));
        writer.write(jsonString);

        writer.flush();
        writer.close();
        os.close();
        
        //==========================================================================
        // Read Response
        //==========================================================================
        if( connection.getInputStream() != null ){
            String line;
            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line=br.readLine()) != null) {
                result.append(line);
            }
        }

        //==========================================================================
        // Log
        //==========================================================================
        if( log.isTraceEnabled() ) {
            for( Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet() ) {
                log.trace("{}:{}", entry.getKey(), entry.getValue() );
            }
        }
        
        return result.toString();
        
    }

}
