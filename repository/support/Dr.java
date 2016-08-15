package zany.repository.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Multiple DataSource 구성 위한 Sample
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151229 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dr {

}
