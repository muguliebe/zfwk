package zany.fwk.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import zany.util.DateUtils;

/**
 * 카운팅 서비스 
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151215 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Service("CountService")
public class CountService extends BaseService{

    private String filePath;
    private String fileName;
    private String fileFullName;
    
    private Gson gson;
    
    private Map<String, Map<String, Long>> map = new HashMap<>();
    
    @Autowired
    public CountService(@Value("${countService.filePath}") String filePath, 
                        @Value("${countService.fileName}") String fileName) throws IOException{
        this.filePath = filePath;
        this.fileName = fileName;
        initilize(filePath, fileName);
    }
    
    /**
     * <pre>
     * - 매일 자정에 카운팅 정보 저장하는 파일을 새로 만든다.
     * - 형식은 yyMMdd
     * </pre>
     * @throws IOException 
     * @throws ZException 
     * @throws Exception void
     */
    @Scheduled(cron = "0 0 00 * * ?")
    public void refresh() throws IOException {
        this.initilize(filePath, fileName);
    }
    
    public void initilize(String filePath, String fileName) throws IOException{

    	// map initilize
    	map = new HashMap<>();
    	
        // File Create Daily
        fileFullName = filePath + fileName + "." + DateUtils.getCurrent("yyMMdd");
        
        File file = new File(fileFullName);
        log.info("Count Service File:" + fileFullName);
        if( file.exists() == false ){
            // Folder Check and Create
            File path = new File(filePath);
            if( path.exists() == false ){
                path.mkdirs();
            }
            
        }
        
        Type type = new TypeToken<Map<String, Map<String, Long>>>(){}.getType();
        gson = new GsonBuilder().setPrettyPrinting().create();
        
        if( file.canRead() ){
            Reader reader;
            reader = new InputStreamReader(new FileInputStream(fileFullName), "UTF-8");
            map = gson.fromJson(reader, type);
        }
        
        add("basic", "run");
        
        // save call
        save();

    }

    /**
     * <pre>
     * CountService Save to File
     * - 매 분마다, 카운팅 정보를 파일에 Write 한다.
     * - 저장은 JSON 형식으로 한다.
     * </pre>
     * @throws IOException 
     * @throws Exception void
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void save() throws IOException {

        add("basic", "save");

        Writer writer = new OutputStreamWriter( new FileOutputStream(fileFullName), "UTF-8");
        gson.toJson(map, writer);
        writer.close();
    }
    
    /**
     * Count Add  
     * @param title 대분류(Caller)
     * @param item  중분류(카운팅 항목명)
     */
    public void add(String title, String item){
        
        Long cnt = get(title, item);
        set(title, item, ++cnt);
        
    }
    
    /**
     * Count Add  
     * @param title 대분류(Caller)
     * @param item  중분류(카운팅 항목명)
     */
    public void minus(String title, String item){
        
        Long cnt = get(title, item);
        set(title, item, --cnt);
        
    }
    
    /**
     * Count Get 
     * @param title 대분류(Caller)
     * @param item  중분류(카운팅 항목명)
     * @return Long Count
     */
    public Long get(String title, String item){
        
        if( map.get(title) == null ){
            return 0L;
        }
        
        Long cnt = map.get(title).get(item);
        if ( cnt == null ){
            return 0L;
        }
        
        return cnt;
    }
    
    /**
     * Count Set  
     * @param title 대분류(Caller)
     * @param item  중분류(카운팅 항목명)
     * @param val   셋팅할 Long Value
     */
    public void set(String title, String item, Long val){
        
        if( map.get(title) == null ){
            map.put(title, new HashMap<String,Long>());
            
        }else if (map.get(title).get(item) == null ){
            map.get(title).put(item, val);

        }
        
        map.get(title).put(item, val);
        
    }
    
}
