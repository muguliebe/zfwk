package zfwk.core.component

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import zfwk.core.model.pojo.ComArea

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class Commons {
    var area: ComArea = ComArea()
}
