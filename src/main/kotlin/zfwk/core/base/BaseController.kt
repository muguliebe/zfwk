package zfwk.core.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

class BaseController : BaseObject() {

    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger

}
