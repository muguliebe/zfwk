package zany.fwk.filter.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 인터셉터
 * - 알아서 동작하는 게 아니라, Webconfig.java 에서 addInterceptor 인자로 넘김
 *
 * @author 윤준호
 * 
 * @version 
 * <ul>
 *  <li> 160706 | 윤준호 | 최초생성 </li>
 * </ul>
 */
public class HttpRequestInterceptor extends HandlerInterceptorAdapter {

    private static Logger log = LoggerFactory.getLogger(HttpRequestInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        if( log.isTraceEnabled() ){
            log.trace("preHandle:" + request.getRequestURI());
        }
        
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
        super.postHandle(request, response, handler, modelAndView);
        
        if( log.isTraceEnabled() ){
            log.trace("postHandle:" + request.getRequestURI());
        }
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        
        super.afterCompletion(request, response, handler, ex);
        
        if( log.isTraceEnabled() ){
            log.trace("afterCompletion:{}", request.getRequestURI());
        }
        
    }
    
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        super.afterConcurrentHandlingStarted(request, response, handler);
        
        if( log.isTraceEnabled() ){
            log.trace("afterConcurrent:{}", request.getRequestURI());
        }
        
    }
    
    
}
