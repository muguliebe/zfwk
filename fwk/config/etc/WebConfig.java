package zany.fwk.config.etc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import zany.fwk.filter.spring.HttpRequestInterceptor;

/**
 * request intercepter
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new HttpRequestInterceptor());
    }
    
    
}
