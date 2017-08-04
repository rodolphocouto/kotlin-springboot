package org.kotlin.springboot.repository.impl

import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.kotlin.springboot.model.Company
import org.kotlin.springboot.model.Product
import org.kotlin.springboot.repository.ProductRepository
import org.kotlin.springboot.repository.impl.mapper.TCompany
import org.kotlin.springboot.repository.impl.mapper.TProduct
import org.kotlin.springboot.repository.impl.mapper.toProduct
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