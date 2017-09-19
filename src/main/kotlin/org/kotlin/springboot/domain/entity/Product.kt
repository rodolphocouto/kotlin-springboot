package org.kotlin.springboot.domain.entity

data class Product(var id: Int,
                   var name: String,
                   var website: String,
                   var industry: Industry,
                   var company: Company)

interface ProductRepository {

    fun findByCompany(company: Company): List<Product>
    fun findByCompanyAndId(company: Company, id: Int): Product?
    fun create(product: Product)
    fun update(product: Product)
    fun remove(product: Product)
    fun removeByCompany(company: Company)
}

class ProductNotFoundException : Exception()