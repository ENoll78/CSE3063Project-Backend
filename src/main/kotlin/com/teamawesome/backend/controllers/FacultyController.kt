package com.teamawesome.backend.controllers

import com.teamawesome.backend.models.Faculty
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.services.FacultyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/faculty")
class FacultyController(
    private val facultyService: FacultyService
) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Faculty> =
        facultyService.getFacultyById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/oauth2/{oauth2Id}")
    fun getByOauth2Id(@PathVariable oauth2Id: String): ResponseEntity<Faculty> =
        facultyService.getFacultyByOauth2Id(oauth2Id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/username/{username}")
    fun getByUsername(@PathVariable username: String): ResponseEntity<Faculty> =
        facultyService.getFacultyByUsername(username)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String): ResponseEntity<Faculty> =
        facultyService.getFacultyByEmail(email)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody faculty: Faculty): ResponseEntity<Faculty> =
        ResponseEntity.ok(facultyService.createFaculty(faculty))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @RequestBody faculty: Faculty
    ): ResponseEntity<Faculty> {
        val existing = facultyService.getFacultyById(id)
            ?: return ResponseEntity.notFound().build()
        faculty.id = existing.id
        return ResponseEntity.ok(facultyService.updateFaculty(faculty))
    }

    @PatchMapping("/{id}/approval")
    fun toggleApproval(
        @PathVariable id: String,
        @RequestParam approved: Boolean
    ): ResponseEntity<Faculty> =
        facultyService.toggleApproval(id, approved)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> =
        if (facultyService.deleteFaculty(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()

    @GetMapping("/students")
    fun getAllStudents(): List<Student> =
        facultyService.getAllStudents().toList()
}
