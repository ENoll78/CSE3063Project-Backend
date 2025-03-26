package com.teamawesome.backend.repositories

import com.teamawesome.backend.models.Admin
import org.springframework.data.repository.CrudRepository
import jakarta.transaction.Transactional

@Transactional
interface AdminRepository : CrudRepository<Admin, String> {

    fun findDistinctByUsername(username: String): Admin?
    fun findDistinctByOauth2Id(oauth2Id: String): Admin?
    fun findDistinctByEmail(email: String): Admin?
}