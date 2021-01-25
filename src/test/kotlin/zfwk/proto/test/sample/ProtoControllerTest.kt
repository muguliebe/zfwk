package zfwk.proto.test.sample

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import zfwk.core.proto.model.ProtoModel
import zfwk.test.base.BaseSpringTest

class ProtoControllerTest : BaseSpringTest() {

    @Test
    fun test() {
        log.info("test start")

        val result = rest.getForObject("/zfwk/proto", ProtoModel::class.java)
        println("result = $result")

        assertThat(result).isNotNull

        log.info("test end")
    }

}
