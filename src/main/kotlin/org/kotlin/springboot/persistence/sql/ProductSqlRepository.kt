package org.kotlin.springboot.persistence.sql

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.Industry
import org.kotlin.springboot.domain.entity.Product
import org.kotlin.springboot.domain.entity.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductSqlRepository : ProductRepository {

    override fun findByCompany(company: Company): List<Product> = (TProduct innerJoin TCompany)
            .select { TProduct.companyId eq company.id }
            .map { toProduct(it) }

    override fun findByCompanyAndId(company: Company, id: Int): Product? = (TProduct innerJoin TCompany)
            .select { TProduct.companyId.eq(company.id) and TProduct.id.eq(id) }
            .map { toProduct(it) }
            .firstOrNull()

    override fun create(product: Product) {
        product.id = TProduct.insert(toTable(product)) get TProduct.id
    }

    override fun update(product: Product) {
        TProduct.update(where = { TProduct.id eq product.id }, body = toTable(product))
    }

    override fun remove(product: Product) {
        TProduct.deleteWhere { TProduct.id eq product.id }
    }

    override fun removeByCompany(company: Company) {
        TProduct.deleteWhere { TProduct.companyId eq company.id }
    }
}

object TProduct : Table("product") {
    val id = TProduct.integer("id").autoIncrement().primaryKey()
    val name = TProduct.text("name")
    val website = TProduct.text("website")
    val industry = TProduct.integer("industry")
    val companyId = TProduct.integer("company_id") references TCompany.id
}

fun toProduct(result: ResultRow) = Product(
        id = result[TProduct.id],
        name = result[TProduct.name],
        website = result[TProduct.website],
        industry = Industry.of(result[TProduct.industry]),
        company = toCompany(result))

fun toTable(product: Product): TProduct.(UpdateBuilder<*>) -> Unit = {
    it[name] = product.name
    it[website] = product.website
    it[industry] = product.industry.id
    it[companyId] = product.company.id
}