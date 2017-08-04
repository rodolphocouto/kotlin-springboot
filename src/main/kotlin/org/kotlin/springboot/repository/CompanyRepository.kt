package org.kotlin.springboot.repository

import org.kotlin.springboot.model.Company

interface CompanyRepository {

    fun findAll(): List<Company>
    fun findById(id: Int): Company?
}