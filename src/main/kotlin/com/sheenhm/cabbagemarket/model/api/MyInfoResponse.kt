package com.sheenhm.cabbagemarket.model.api

import com.sheenhm.cabbagemarket.repository.MyInfo
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "MyInfo", title = "회원 정보(API)")
data class MyInfoResponse(
    var id: Int,

    @Schema(title = "회원 ID")
    var userId: String,

    @Schema(title = "회원 이름")
    var name: String?,

    @Schema(title = "회원 연락처")
    var tel: String?,

    @Schema(title = "회원 주소 : X")
    var geoX: Double?,

    @Schema(title = "회원 주소 : Y")
    var geoY: Double?
) {
    companion object {
        fun myInfo2Response(myinfo: MyInfo): MyInfoResponse {
            return MyInfoResponse(myinfo.id, myinfo.userId, myinfo.name, myinfo.tel, myinfo.geoX, myinfo.geoY)
        }
    }
}