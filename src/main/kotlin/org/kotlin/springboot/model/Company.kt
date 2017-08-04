package org.kotlin.springboot.model

import org.joda.time.LocalDate
import org.springframework.hateoas.core.Relation
import java.math.BigDecimal

@Relation(collectionRelation = "companies")
data class Company(var id: Int,
                   var name: String,
                   var website: String,
                   var revenue: BigDecimal,
                   var employees: Int,
                   var headquarters: String,
                   var founded: LocalDate)