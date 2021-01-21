package zfwk.core.proto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["zfwk"],
    exclude = [DataSourceAutoConfiguration::class]
)
class ZFwkApp

fun main(args: Array<String>) {
    runApplication<ZFwkApp>(*args)
}
