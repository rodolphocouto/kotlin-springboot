package org.kotlin.springboot.persistence.mapper

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.kotlin.springboot.domain.entity.Company

object TCompany : Table("company") {
    val id = integer("id")
    val name = text("name")
    val website = text("website")
    val revenue = decimal("revenue", 10, 2)
    val employees = integer("employees")
    val headquarters = text("headquarters")
    val founded = date("founded")
}

fun toCompany(result: ResultRow) = Company(
        id = result[TCompany.id],
        name = result[TCompany.name],
        website = result[TCompany.website],
        revenue = result[TCompany.revenue],
        employees = result[TCompany.employees],
        headquarters = result[TCompany.headquarters],
        founded = result[TCompany.founded].toLocalDate())