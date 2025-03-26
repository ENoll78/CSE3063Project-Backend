package com.teamawesome.backend.repositories

import com.teamawesome.backend.models.Student
import org.springframework.data.repository.CrudRepository
import jakarta.transaction.Transactional

/**
 *
 * Interesting fact here: Traditionally we'd invoke an:
 * Optional<UserType> findById(String id);
 *
 * This is handled by the extension function, so we can
 * automatically call findById() w/o implementation
 */
@Transactional
interface StudentRepository : CrudRepository<Student, String> {

    // Find Student by username, return null if a miss
    fun findDistinctByUsername(username: String): Student?

    // Find Student by OAuth2 identifier, return null if a miss
    fun findDistinctByOauth2Id(id: String): Student?

    // Find Student by email, return null if a miss
    fun findDistinctByEmail(email: String): Student?
}