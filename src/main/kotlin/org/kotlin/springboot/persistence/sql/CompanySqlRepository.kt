package org.kotlin.springboot.persistence.sql

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.CompanyRepository
import org.springframework.stereotype.Repository

@Repository
class CompanySqlRepository : CompanyRepository {

    override fun findAll(): List<Company> = TCompany
            .selectAll()
            .map { toCompany(it) }

    override fun findById(id: Int): Company? = TCompany
            .select { TCompany.id eq id }
            .map { toCompany(it) }
            .firstOrNull()

    override fun create(company: Company) {
        company.id = TCompany.insert(toTable(company)) get TCompany.id
    }

    override fun update(company: Company) {
        TCompany.update(where = { TCompany.id eq company.id }, body = toTable(company))
    }

    override fun remove(company: Company) {
        TCompany.deleteWhere { TCompany.id eq company.id }
    }
}

object TCompany : Table("company") {
    val id = TCompany.integer("id").autoIncrement().primaryKey()
    val name = TCompany.text("name")
    val website = TCompany.text("website")
    val revenue = TCompany.decimal("revenue", 10, 2)
    val employees = TCompany.integer("employees")
    val headquarters = TCompany.text("headquarters")
    val founded = TCompany.date("founded")
}

fun toCompany(result: ResultRow) = Company(
        id = result[TCompany.id],
        name = result[TCompany.name],
        website = result[TCompany.website],
        revenue = result[TCompany.revenue],
        employees = result[TCompany.employees],
        headquarters = result[TCompany.headquarters],
        founded = result[TCompany.founded].toLocalDate())

fun toTable(company: Company): TCompany.(UpdateBuilder<*>) -> Unit = {
    it[name] = company.name
    it[website] = company.website
    it[revenue] = company.revenue
    it[employees] = company.employees
    it[headquarters] = company.headquarters
    it[founded] = company.founded.toDateTimeAtStartOfDay()
}