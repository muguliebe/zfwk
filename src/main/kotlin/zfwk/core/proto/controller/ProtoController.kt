package zfwk.core.proto.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zfwk.core.base.BaseController
import zfwk.core.proto.model.ProtoModel
import zfwk.core.proto.service.ProtoService

@RestController
@RequestMapping("/zfwk/proto")
class ProtoController : BaseController() {

    @Autowired lateinit var service: ProtoService

    @GetMapping("/")
    fun protoGet(): ProtoModel = service.test()

}
