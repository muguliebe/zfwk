package zany.repository.support;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SKP Mail DB
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 160520 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mail {

}
