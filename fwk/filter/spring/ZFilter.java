package zany.fwk.filter.spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Logger;

/**
 * 서블릿 필터.
 *
 * @author 윤준호
 * @version <ul>
 *  <li> 151119 | 윤준호 | 최초생성 </li>
 * </ul>
 * @see <ul>
 *  <li>151222 | 윤준호 | 현재 미사용
 * </ul>
 */
@Configuration
public class ZFilter implements Filter{

    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    
//    @Bean
    public Filter zfilter(){
        return new ZFilter();
    }
    
    /**
     * ZFilter Spring Container 등록.
     */
//    @Bean
    public FilterRegistrationBean registFilter(){
        
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter( zfilter() );
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("zFilter");
        
        log.info("ZFilter registered..");
        
        return registration;
        
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        if( log.isTraceEnabled() ){
            log.trace("zFilter Start by "+ request.getRemoteAddr());
        }
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper((HttpServletRequest) request);
        BufferedReader reader = new BufferedReader(new InputStreamReader( requestWrapper.getInputStream() ));
        StringBuilder result = new StringBuilder();
        
        String line;
        while ((line=reader.readLine()) != null) {
            result.append(line);
        }
        requestWrapper.setAttribute("bodyContent", result.toString());
        
        if( log.isTraceEnabled() ){
            log.trace("zFilter End");
        }
        
        chain.doFilter(requestWrapper, response);
        
    }


}
