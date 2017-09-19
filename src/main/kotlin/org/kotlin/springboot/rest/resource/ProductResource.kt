package org.kotlin.springboot.rest.resource

import org.kotlin.springboot.CompanyEndpoint
import org.kotlin.springboot.domain.entity.Company
import org.kotlin.springboot.domain.entity.Industry
import org.kotlin.springboot.domain.entity.Product
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Relation(collectionRelation = "products")
data class ProductResource(val name: String,
                           val website: String,
                           val industry: Industry) : ResourceSupport()

@Component
class ProductResourceAssembler : ResourceAssembler<Product, ProductResource> {

    fun toResources(products: Iterable<Product>): Resources<ProductResource> = Resources(products.map { toResource(it) })

    override fun toResource(product: Product): ProductResource {
        val resource = ProductResource(
                name = product.name,
                website = product.website,
                industry = product.industry)

        resource.add(linkTo(methodOn(CompanyEndpoint::class.java).findProductByCompanyAndId(product.company.id, product.id)).withSelfRel())

        return resource
    }

    fun toProduct(resource: ProductResource, company: Company, id: Int): Product = Product(
            id = id,
            name = resource.name,
            website = resource.website,
            industry = resource.industry,
            company = company)
}