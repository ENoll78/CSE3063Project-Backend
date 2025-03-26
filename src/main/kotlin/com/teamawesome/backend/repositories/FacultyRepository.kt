package com.teamawesome.backend.repositories

import com.teamawesome.backend.models.Faculty
import org.springframework.data.repository.CrudRepository
import jakarta.transaction.Transactional

@Transactional
interface FacultyRepository : CrudRepository<Faculty, String> {

    fun findDistinctByUsername(username: String): Faculty?
    fun findDistinctByOauth2Id(oauth2Id: String): Faculty?
    fun findDistinctByEmail(email: String): Faculty?
}