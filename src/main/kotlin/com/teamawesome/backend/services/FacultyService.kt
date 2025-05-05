package com.teamawesome.backend.services

import com.teamawesome.backend.models.Faculty
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.repositories.FacultyRepository
import com.teamawesome.backend.repositories.StudentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * Service for CRUD, business logic related to Faculty
 *
 * Any operation that has to do with Faculty goes here
 *
 * Example:
 * CRUD Ops
 * Admin approval toggle
 */

@Service
class FacultyService(
    private val facultyRepository: FacultyRepository,
    private val studentRepository: StudentRepository
    ) {

    // Retrieve Faculty member by unique id
    fun getFacultyById(id: String): Faculty? {
        return facultyRepository.findByIdOrNull(id)
    }

    // Retrieve Faculty member by OAuth2 Id.
    // I'm not sure yet how something like GoogleAuth works here.
    fun getFacultyByOauth2Id(oauth2Id: String): Faculty? {
        return facultyRepository.findDistinctByOauth2Id(oauth2Id)
    }

    // " " by username
    fun getFacultyByUsername(username: String): Faculty? {
        return facultyRepository.findDistinctByUsername(username)
    }

    // " " by e-mail
    fun getFacultyByEmail(email: String): Faculty? {
        return facultyRepository.findDistinctByEmail(email)
    }

    // Create a faculty user
    fun createFaculty(faculty: Faculty): Faculty {
        faculty.id = null
        return facultyRepository.save(faculty)
    }

    // Update existing faculty user
    fun updateFaculty(faculty: Faculty): Faculty {
        return facultyRepository.save(faculty)
    }

    // Admin Only: Toggle approval of Faculty user
    fun toggleApproval(id: String, approved: Boolean): Faculty? {
        val faculty = getFacultyById(id) ?: return null
        faculty.approved = approved
        return updateFaculty(faculty)
    }

    // Delete existing faculty user
    fun deleteFaculty(id: String): Boolean {
        return if (facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id)
            true
        } else false
    }

    fun getAllStudents(): Iterable<Student> =
        studentRepository.findAll()
}