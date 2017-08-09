package org.kotlin.springboot.domain.repository

import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.Product

interface ProductRepository {

    fun findByCompany(company: Company): List<Product>
    fun findByCompanyAndId(company: Company, id: Int): Product?
}