package org.kotlin.springboot.domain.entity

import org.joda.time.LocalDate
import java.math.BigDecimal

data class Company(var id: Int,
                   var name: String,
                   var website: String,
                   var revenue: BigDecimal,
                   var employees: Int,
                   var headquarters: String,
                   var founded: LocalDate)

interface CompanyRepository {

    fun findAll(): List<Company>
    fun findById(id: Int): Company?
    fun create(company: Company)
    fun update(company: Company)
    fun remove(company: Company)
}

class CompanyNotFoundException : Exception()