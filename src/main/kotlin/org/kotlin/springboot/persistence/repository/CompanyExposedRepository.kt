package org.kotlin.springboot.persistence.repository

import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.repository.CompanyRepository
import org.kotlin.springboot.persistence.mapper.TCompany
import org.kotlin.springboot.persistence.mapper.toCompany
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