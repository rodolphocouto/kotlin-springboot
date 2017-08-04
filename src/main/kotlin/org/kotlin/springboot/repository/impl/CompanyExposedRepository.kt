package org.kotlin.springboot.repository.impl

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.kotlin.springboot.model.Company
import org.kotlin.springboot.repository.CompanyRepository
import org.kotlin.springboot.repository.impl.mapper.TCompany
import org.kotlin.springboot.repository.impl.mapper.toCompany
import org.springframework.stereotype.Repository

@Repository
class CompanyExposedRepository : CompanyRepository {

    override fun findAll(): List<Company> = TCompany
            .selectAll()
            .map { toCompany(it) }

    override fun findById(id: Int): Company? = TCompany
            .select { TCompany.id eq id }
            .map { toCompany(it) }
            .firstOrNull()
}