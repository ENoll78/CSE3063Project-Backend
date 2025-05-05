package com.teamawesome.backend.services

import com.teamawesome.backend.models.Admin
import com.teamawesome.backend.models.Faculty
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.repositories.AdminRepository
import com.teamawesome.backend.repositories.FacultyRepository
import com.teamawesome.backend.repositories.StudentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * Everything Admin
 */
@Service
class AdminService(
    private val adminRepository: AdminRepository,
    private val facultyRepository: FacultyRepository,
    private val studentRepository: StudentRepository
) {

    fun getAdminById(id: String): Admin? {
        return adminRepository.findByIdOrNull(id)
    }

    fun getAdminByOauth2Id(oauth2Id: String): Admin? {
        return adminRepository.findDistinctByOauth2Id(oauth2Id)
    }

    fun getAdminByUsername(username: String): Admin? {
        return adminRepository.findDistinctByUsername(username)
    }

    fun getAdminByEmail(email: String): Admin? {
        return adminRepository.findDistinctByEmail(email)
    }

    fun createAdmin(admin: Admin): Admin {
        admin.id = null
        return adminRepository.save(admin)
    }

    fun updateAdmin(admin: Admin): Admin {
        return adminRepository.save(admin)
    }

    fun deleteAdmin(id: String): Boolean {
        return if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id)
            true
        } else false
    }

    fun getAllAdmins(): Iterable<Admin> =
        adminRepository.findAll()

    fun getAllFaculty(): Iterable<Faculty> =
        facultyRepository.findAll()

    fun getAllStudents(): Iterable<Student> =
        studentRepository.findAll()
}