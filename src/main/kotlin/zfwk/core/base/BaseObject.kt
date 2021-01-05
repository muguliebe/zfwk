package zfwk.core.base

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

/**
 * 최상위 클래스
 */
abstract class BaseObject {

    @Autowired lateinit var ctx: ApplicationContext

}
