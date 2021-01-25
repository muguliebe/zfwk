package zfwk.core.proto.service

import org.springframework.stereotype.Service
import zfwk.core.base.BaseService
import zfwk.core.proto.model.ProtoModel

@Service
class ProtoService :BaseService() {
    fun test() = ProtoModel(one = "one", two = "two")
}
