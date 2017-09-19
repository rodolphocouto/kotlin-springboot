package org.kotlin.springboot.domain.service

import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.CompanyRepository
import org.kotlin.springboot.domain.entity.Product
import org.kotlin.springboot.domain.entity.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompanyService(val companyRepository: CompanyRepository,
                     val productRepository: ProductRepository) {

    fun findAll(): List<Company> = companyRepository.findAll()
    fun findById(id: Int): Company? = companyRepository.findById(id)
    fun create(company: Company) = companyRepository.create(company)
    fun update(company: Company) = companyRepository.update(company)
    fun remove(company: Company) {
        productRepository.removeByCompany(company)
        companyRepository.remove(company)
    }

    fun findProductsByCompany(company: Company): List<Product> = productRepository.findByCompany(company)
    fun findProductByCompanyAndId(company: Company, id: Int): Product? = productRepository.findByCompanyAndId(company, id)
    fun createProduct(product: Product) = productRepository.create(product)
    fun updateProduct(product: Product) = productRepository.update(product)
    fun removeProduct(product: Product) = productRepository.remove(product)
}