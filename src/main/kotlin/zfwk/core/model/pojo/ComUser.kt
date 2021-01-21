package zfwk.core.model.pojo

import java.io.Serializable

data class ComUser(
    var sessId: String = "",
    var userId: String,
    val email: String = "",
    val roleIds: String = "",
    val deptId: String = "",
    var deptName: String = "",
    val jwt: String = "",
    val isMobYn: String = "N",
    val mobType: String = ""
) : Serializable
