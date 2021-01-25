package zfwk.test.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory

open class BaseTest {
    private fun <T> loggerFor(clazz: Class<T>) = LoggerFactory.getLogger(clazz) as Logger
    val log = loggerFor(javaClass)
}
