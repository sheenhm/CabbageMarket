package com.sheenhm.cabbagemarket.repository

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "my_info")
data class MyInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Int,

    @Column(nullable = false)
    var userId: String,

    var name: String?,

    var tel: String?,

    @Column(name = "geo_x")
    var geoX: Double?,

    @Column(name = "geo_y")
    var geoY: Double?
) {
    constructor() : this(0, "", null,null,null,null)
}

@Repository
interface MyInfoRepository: JpaRepository<MyInfo, String> {
    fun findByUserId(userId: String): MyInfo?
}