package org.kotlin.springboot.persistence.mapper

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.kotlin.springboot.domain.entity.Industry
import org.kotlin.springboot.domain.entity.Product

object TProduct : Table("product") {
    val id = integer("id")
    val name = text("name")
    val website = text("website")
    val industry = integer("industry")
    val companyId = integer("company_id") references TCompany.id
}

fun toProduct(result: ResultRow) = Product(
        id = result[TProduct.id],
        name = result[TProduct.name],
        website = result[TProduct.website],
        industry = Industry.of(result[TProduct.industry]),
        company = toCompany(result))