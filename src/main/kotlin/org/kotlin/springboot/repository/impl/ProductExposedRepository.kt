package org.kotlin.springboot.repository.impl

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.kotlin.springboot.model.Company
import org.kotlin.springboot.model.Industry
import org.kotlin.springboot.model.Product
import org.kotlin.springboot.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductExposedRepository : ProductRepository {

    object TProduct : Table("product") {
        val id = integer("id")
        val name = text("name")
        val website = text("website")
        val industry = integer("industry")
        val companyId = integer("company_id")
    }

    private fun fromRow(result: ResultRow) = Product(
            id = result[TProduct.id],
            name = result[TProduct.name],
            website = result[TProduct.website],
            industry = Industry.of(result[TProduct.industry]))

    override fun findByCompany(company: Company): List<Product> = TProduct
            .select { TProduct.companyId eq company.id }
            .map { fromRow(it) }

    override fun findByCompanyAndId(company: Company, id: Int): Product? = TProduct
            .select { TProduct.companyId.eq(company.id) and TProduct.id.eq(id) }
            .map { fromRow(it) }
            .firstOrNull()
}