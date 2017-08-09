package org.kotlin.springboot

import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.CompanyNotFoundException
import org.kotlin.springboot.domain.entity.Product
import org.kotlin.springboot.domain.entity.ProductNotFoundException
import org.kotlin.springboot.domain.service.CompanyService
import org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/companies")
class CompanyResource(val companyService: CompanyService) {

    @GetMapping(produces = arrayOf(HAL_JSON_VALUE))
    fun findAll(): ResponseEntity<Resources<Resource<Company>>> {
        return ok(Resources(companyService.findAll().map { toResource(it) }))
    }

    @GetMapping("/{id}", produces = arrayOf(HAL_JSON_VALUE))
    fun findById(@PathVariable("id") id: Int): ResponseEntity<Resource<Company>> {
        return ok(toResource(companyService.findById(id) ?: throw CompanyNotFoundException()))
    }

    @GetMapping("/{id}/products", produces = arrayOf(HAL_JSON_VALUE))
    fun findProductsByCompany(@PathVariable("id") id: Int): ResponseEntity<Resources<Resource<Product>>> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        return ok(Resources(companyService.findProductsByCompany(company).map { toResource(it) }))
    }

    @GetMapping("/{id}/products/{idProduct}", produces = arrayOf(HAL_JSON_VALUE))
    fun findProductByCompanyAndId(@PathVariable("id") id: Int, @PathVariable("idProduct") idProduct: Int): ResponseEntity<Resource<Product>> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        return ok(toResource(companyService.findProductByCompanyAndId(company, idProduct) ?: throw ProductNotFoundException()))
    }

    private fun toResource(company: Company): Resource<Company> = Resource(company,
            linkTo(methodOn(CompanyResource::class.java).findById(company.id)).withSelfRel(),
            linkTo(methodOn(CompanyResource::class.java).findProductsByCompany(company.id)).withRel("products"))

    private fun toResource(product: Product): Resource<Product> = Resource(product,
            linkTo(methodOn(CompanyResource::class.java).findProductByCompanyAndId(product.company.id, product.id)).withSelfRel())
}