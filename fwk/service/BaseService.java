package zany.fwk.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import ch.qos.logback.classic.Logger;

/**
 * 서비스 에서 상속받아야만 하는 부모클래스
 * 
 * @author 윤준호
 * @see
 * <ul>
 *  <li>서비스 로그 생성 규칙
 *      <ul>
 *          <li>서비스이름_날짜(yyyyMMdd)</li>
 *      </ul>
 *  </li>
 * </ul>
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
public abstract class BaseService {

    protected Logger log = (Logger) LoggerFactory.getLogger("eachService");    // 서비스 로그
    
    @Autowired
    protected ApplicationContext ctx;
    
}
