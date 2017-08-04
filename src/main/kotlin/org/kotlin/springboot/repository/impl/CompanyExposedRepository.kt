package org.kotlin.springboot.repository.impl

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.kotlin.springboot.model.Company
import org.kotlin.springboot.repository.CompanyRepository
import org.springframework.stereotype.Repository

@Repository
class CompanyExposedRepository : CompanyRepository {

    object TCompany : Table("company") {
        val id = integer("id")
        val name = text("name")
        val website = text("website")
        val revenue = decimal("revenue", 10, 2)
        val employees = integer("employees")
        val headquarters = text("headquarters")
        val founded = date("founded")
    }

    private fun fromRow(result: ResultRow) = Company(
            id = result[TCompany.id],
            name = result[TCompany.name],
            website = result[TCompany.website],
            revenue = result[TCompany.revenue],
            employees = result[TCompany.employees],
            headquarters = result[TCompany.headquarters],
            founded = result[TCompany.founded].toLocalDate())

    override fun findAll(): List<Company> = TCompany
            .selectAll()
            .map { fromRow(it) }

    override fun findById(id: Int): Company? = TCompany
            .select { TCompany.id eq id }
            .map { fromRow(it) }
            .firstOrNull()
}