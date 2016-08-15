package zany.fwk.filter.spring;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

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
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private static Logger log = LoggerFactory.getLogger(WebSocketInterceptor.class);

    @Override
    public boolean beforeHandshake(  ServerHttpRequest request
                                     , ServerHttpResponse response
                                     , WebSocketHandler handler
                                     , Map<String, Object> attribute
    ) throws Exception {
        log.debug("websocket intercept before ==> " + request.getRemoteAddress());
        attribute.put("ip", request.getRemoteAddress());
        return super.beforeHandshake(request, response, handler, attribute);
    }
    
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        log.debug("websocket intercept after  ==> " + request.getRemoteAddress());
        super.afterHandshake(request, response, wsHandler, ex);
    }


    
}
