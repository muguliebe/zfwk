package zany.fwk.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import zany.fwk.service.TestService;
import zany.repository.entity.AllTable;
import zany.util.SystemInfo;

/**
 * The Class TestController.
 */
@RestController
@Service
@RequestMapping("/test")
public class TestController extends BaseController{

    @RequestMapping(value="", method=RequestMethod.GET)
    public String testRoot() throws SigarException{
        return "i'm alive";
    }
    
    /**
     * Test.
     *
     * @throws ZException the z exception
     * @throws SigarException 
     */
    @RequestMapping(value="/1", method=RequestMethod.GET)
    public String testGet() throws SigarException{
        Gson gson = new Gson();
        SystemInfo.refresh();
        String result = gson.toJson(SystemInfo.getEntity());
        
        return result;
    }
    @RequestMapping(value="/1", method=RequestMethod.POST)
    public String testPost() throws SigarException{
        Gson gson = new Gson();
        SystemInfo.refresh();
        String result = gson.toJson(SystemInfo.getEntity());
        
        return result;
    }
    @RequestMapping(value="/systems", method=RequestMethod.POST)
    public String systems() throws SigarException{
        Gson gson = new Gson();
        SystemInfo.refresh();
        String result = gson.toJson(SystemInfo.getEntity());
        
        return result;
    }
    
    @RequestMapping(value="/2", method=RequestMethod.GET)
    public String test2() throws SigarException, IOException{
        
        String rtnStr = "";
        String s = null;
        
        Process p = Runtime.getRuntime().exec("ls -l");
        
        BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
             InputStreamReader(p.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            rtnStr += s + "\n";
        }
         
        // read any errors from the attempted command
        String s2 = null;
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s2 = stdError.readLine()) != null) {
            System.out.println(s2);
        }
        
        return rtnStr;
    }

    @RequestMapping(value="tablesJpa", method=RequestMethod.GET)
    public List<AllTable> getAllTables() throws SigarException{
        
        TestService ts = ctx.getBean(TestService.class);
        List<AllTable> tables = ts.getTables();
        
        return tables;
    }
    
    @RequestMapping(value="tables", method=RequestMethod.GET)
    public List<Map<String, Object>> getAllTablesJdbc() throws SigarException{
        
        TestService ts = ctx.getBean(TestService.class);
        List<Map<String, Object>> tablesByJdbc = ts.getTablesByJdbc();
        
        return tablesByJdbc;
    }

}
