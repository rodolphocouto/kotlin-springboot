package org.kotlin.springboot

import org.kotlin.springboot.domain.entity.CompanyNotFoundException
import org.kotlin.springboot.domain.entity.ProductNotFoundException
import org.kotlin.springboot.domain.service.CompanyService
import org.kotlin.springboot.rest.resource.CompanyResource
import org.kotlin.springboot.rest.resource.CompanyResourceAssembler
import org.kotlin.springboot.rest.resource.ProductResource
import org.kotlin.springboot.rest.resource.ProductResourceAssembler
import org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE
import org.springframework.hateoas.Resources
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri

@RestController
@RequestMapping("/companies")
class CompanyEndpoint(val companyService: CompanyService,
                      val companyResourceAssembler: CompanyResourceAssembler,
                      val productResourceAssembler: ProductResourceAssembler) {

    @GetMapping(produces = arrayOf(HAL_JSON_VALUE))
    fun findAll(): ResponseEntity<Resources<CompanyResource>> {
        val companies = companyService.findAll()
        return ok(companyResourceAssembler.toResources(companies))
    }

    @GetMapping("/{id}", produces = arrayOf(HAL_JSON_VALUE))
    fun findById(@PathVariable("id") id: Int): ResponseEntity<CompanyResource> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        return ok(companyResourceAssembler.toResource(company))
    }

    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun create(@RequestBody resource: CompanyResource): ResponseEntity<Unit> {
        val company = companyResourceAssembler.toCompany(resource, id = 0)
        companyService.create(company)
        return ResponseEntity.created(fromCurrentRequestUri().path("/{id}").buildAndExpand(company.id).toUri()).build()
    }

    @PutMapping("/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun update(@PathVariable("id") id: Int,
               @RequestBody resource: CompanyResource): ResponseEntity<Unit> {
        val company = companyResourceAssembler.toCompany(resource, id)
        companyService.update(company)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        companyService.remove(company)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/{id}/products", produces = arrayOf(HAL_JSON_VALUE))
    fun findProductsByCompany(@PathVariable("id") id: Int): ResponseEntity<Resources<ProductResource>> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        val products = companyService.findProductsByCompany(company)
        return ok(productResourceAssembler.toResources(products))
    }

    @GetMapping("/{id}/products/{idProduct}", produces = arrayOf(HAL_JSON_VALUE))
    fun findProductByCompanyAndId(@PathVariable("id") id: Int,
                                  @PathVariable("idProduct") idProduct: Int): ResponseEntity<ProductResource> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        val product = companyService.findProductByCompanyAndId(company, idProduct) ?: throw ProductNotFoundException()
        return ok(productResourceAssembler.toResource(product))
    }

    @PostMapping("/{id}/products", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun createProduct(@PathVariable("id") id: Int,
                      @RequestBody resource: ProductResource): ResponseEntity<Unit> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        val product = productResourceAssembler.toProduct(resource, company, id = 0)
        companyService.createProduct(product)
        return ResponseEntity.created(fromCurrentRequestUri().path("/{id}").buildAndExpand(product.id).toUri()).build()
    }

    @PutMapping("/{id}/products/{idProduct}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun updateProduct(@PathVariable("id") id: Int,
                      @PathVariable("idProduct") idProduct: Int,
                      @RequestBody resource: ProductResource): ResponseEntity<Unit> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        val product = productResourceAssembler.toProduct(resource, company, idProduct)
        companyService.updateProduct(product)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}/products/{idProduct}")
    fun removeProduct(@PathVariable("id") id: Int,
                      @PathVariable("idProduct") idProduct: Int): ResponseEntity<Unit> {
        val company = companyService.findById(id) ?: throw CompanyNotFoundException()
        val product = companyService.findProductByCompanyAndId(company, idProduct) ?: throw CompanyNotFoundException()
        companyService.removeProduct(product)
        return ResponseEntity.noContent().build()
    }
}