package zfwk

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import zfwk.core.proto.ZFwkApp

@SpringBootTest(
    classes = [ZFwkApp::class]
)
class ZfwkTest{

    @Test
    fun contextLoads() {
    }

}
