package zfwk.core.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional

@Transactional
abstract class BaseService {

    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger

}
