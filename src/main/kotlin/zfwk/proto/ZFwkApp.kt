package zfwk.proto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = "zfwk")
class ZFwkApplication

fun main(args: Array<String>) {
    runApplication<ZFwkApplication>(*args)
}
