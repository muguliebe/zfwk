package zany.fwk.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;

/**
 * Controller 에서 상속받아야만 하는 부모 클래스
 * 
 * @author 윤준호
 * @see
 * <ul>
 *  <li>콘트롤러 Logger 는 Root 로거에 기록</li>
 * </ul>
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
public class BaseController {

    protected Logger log = (Logger) LoggerFactory.getLogger(this.getClass());    // Logger
    
    @Autowired
    protected ApplicationContext ctx;   // Spring Application Context
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception, HttpServletRequest request) {
        
//        log.error("Exception", exception);
        DefaultExceptionResponse response = new DefaultExceptionResponse(request, exception);
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(response));
        
    }

}
