package com.sheenhm.cabbagemarket.model.dto

import com.sheenhm.cabbagemarket.repository.MyInfo
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserInfo", title = "회원 정보(DTO)")
data class UserInfo (
    val userId: String,
    val name: String,
    val tel: String,
    val geoX: Double,
    val geoY: Double
) {
    fun toMyInfo(): MyInfo {
        return MyInfo(
            id = 0,
            userId = this.userId,
            geoX = this.geoX,
            geoY = this.geoY,
            name = this.name,
            tel = this.tel
        )
    }
}