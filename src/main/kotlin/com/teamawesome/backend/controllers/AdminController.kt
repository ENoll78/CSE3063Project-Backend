package com.teamawesome.backend.controllers

import com.teamawesome.backend.models.Admin
import com.teamawesome.backend.models.Faculty
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.services.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admins")
class AdminController(private val adminService: AdminService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Admin> =
        adminService.getAdminById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/oauth2/{oauth2Id}")
    fun getByOauth2Id(@PathVariable oauth2Id: String): ResponseEntity<Admin> =
        adminService.getAdminByOauth2Id(oauth2Id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/username/{username}")
    fun getByUsername(@PathVariable username: String): ResponseEntity<Admin> =
        adminService.getAdminByUsername(username)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String): ResponseEntity<Admin> =
        adminService.getAdminByEmail(email)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping
    fun getAllAdmins(): List<Admin> =
        adminService.getAllAdmins().toList()

    @PostMapping
    fun create(@RequestBody admin: Admin): ResponseEntity<Admin> =
        ResponseEntity.ok(adminService.createAdmin(admin))

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody admin: Admin): ResponseEntity<Admin> {
        val existing = adminService.getAdminById(id) ?: return ResponseEntity.notFound().build()
        admin.id = existing.id
        return ResponseEntity.ok(adminService.updateAdmin(admin))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> =
        if (adminService.deleteAdmin(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()

    @GetMapping("/faculty")
    fun getAllFaculty(): List<Faculty> =
        adminService.getAllFaculty().toList()

    @GetMapping("/students")
    fun getAllStudents(): List<Student> =
        adminService.getAllStudents().toList()
}
