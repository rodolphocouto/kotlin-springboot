package org.kotlin.springboot.domain.service

import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.Product
import org.kotlin.springboot.domain.repository.CompanyRepository
import org.kotlin.springboot.domain.repository.ProductRepository
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