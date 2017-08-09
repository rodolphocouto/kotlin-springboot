package org.kotlin.springboot.persistence.repository

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.Product
import org.kotlin.springboot.domain.repository.ProductRepository
import org.kotlin.springboot.persistence.mapper.TCompany
import org.kotlin.springboot.persistence.mapper.TProduct
import org.kotlin.springboot.persistence.mapper.toProduct
import org.springframework.stereotype.Repository

@Repository
class ProductExposedRepository : ProductRepository {

    override fun findByCompany(company: Company): List<Product> = (TProduct innerJoin TCompany)
            .select { TProduct.companyId eq company.id }
            .map { toProduct(it) }

    override fun findByCompanyAndId(company: Company, id: Int): Product? = (TProduct innerJoin TCompany)
            .select { TProduct.companyId.eq(company.id) and TProduct.id.eq(id) }
            .map { toProduct(it) }
            .firstOrNull()
}