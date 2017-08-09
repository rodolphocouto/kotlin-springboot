package org.kotlin.springboot.domain.repository

import org.kotlin.springboot.domain.entity.Company

interface CompanyRepository {

    fun findAll(): List<Company>
    fun findById(id: Int): Company?
}