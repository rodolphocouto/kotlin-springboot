package org.kotlin.springboot.rest.resource

import org.joda.time.LocalDate
import org.kotlin.springboot.CompanyEndpoint
import org.kotlin.springboot.domain.entity.Company
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Relation(collectionRelation = "companies")
data class CompanyResource(val name: String,
                           val website: String,
                           val revenue: BigDecimal,
                           val employees: Int,
                           val headquarters: String,
                           val founded: LocalDate) : ResourceSupport()

@Component
class CompanyResourceAssembler : ResourceAssembler<Company, CompanyResource> {

    fun toResources(companies: Iterable<Company>): Resources<CompanyResource> = Resources(companies.map { toResource(it) })

    override fun toResource(company: Company): CompanyResource {
        val resource = CompanyResource(
                name = company.name,
                website = company.website,
                revenue = company.revenue,
                employees = company.employees,
                headquarters = company.headquarters,
                founded = company.founded)

        resource.add(linkTo(methodOn(CompanyEndpoint::class.java).findById(company.id)).withSelfRel())
        resource.add(linkTo(methodOn(CompanyEndpoint::class.java).findProductsByCompany(company.id)).withRel("products"))

        return resource
    }

    fun toCompany(resource: CompanyResource, id: Int): Company = Company(
            id = id,
            name = resource.name,
            website = resource.website,
            revenue = resource.revenue,
            employees = resource.employees,
            headquarters = resource.headquarters,
            founded = resource.founded)
}