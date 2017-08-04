package org.kotlin.springboot.model

import org.springframework.hateoas.core.Relation

@Relation(collectionRelation = "products")
data class Product(var id: Int,
                   var name: String,
                   var website: String,
                   var industry: Industry)