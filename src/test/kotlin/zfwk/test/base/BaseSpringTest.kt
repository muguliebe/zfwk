package zfwk.test.base

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.ApplicationContext
import zfwk.core.proto.ZFwkApp

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
    , classes = [ZFwkApp::class]
)
class BaseSpringTest :BaseTest() {

    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var rest: TestRestTemplate

    // URL 호출을 위한 entry point
    val protocol: String = "http://"
    val host: String = "localhost"
    @LocalServerPort var port: Int = 0

    // URL
    lateinit var basicUri: String
    lateinit var token: String

}
