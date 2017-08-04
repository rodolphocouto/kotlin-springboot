package org.kotlin.springboot.service

import org.kotlin.springboot.model.Company
import org.kotlin.springboot.model.Product
import org.kotlin.springboot.repository.CompanyRepository
import org.kotlin.springboot.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompanyService(val companyRepository: CompanyRepository,
                     val productRepository: ProductRepository) {

    fun findAll(): List<Company> = companyRepository.findAll()
    fun findById(id: Int): Company? = companyRepository.findById(id)

    fun findProductsByCompany(company: Company): List<Product> = productRepository.findByCompany(company)
    fun findProductByCompanyAndId(company: Company, id: Int): Product? = productRepository.findByCompanyAndId(company, id)
}