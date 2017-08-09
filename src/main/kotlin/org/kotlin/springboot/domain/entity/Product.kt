package org.kotlin.springboot.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.hateoas.core.Relation

@Relation(collectionRelation = "products")
data class Product(var id: Int,
                   var name: String,
                   var website: String,
                   var industry: Industry,
                   @JsonIgnore var company: Company)

class ProductNotFoundException : Exception()