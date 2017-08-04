package org.kotlin.springboot.repository

import org.kotlin.springboot.model.Company
import org.kotlin.springboot.model.Product

interface ProductRepository {

    fun findByCompany(company: Company): List<Product>
    fun findByCompanyAndId(company: Company, id: Int): Product?
}