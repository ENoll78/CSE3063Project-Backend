package com.teamawesome.backend.controllers

import com.teamawesome.backend.models.CourseGrade
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.services.StudentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/students")
class StudentController(private val studentService: StudentService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Student> =
        studentService.getStudentById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/oauth2/{oauth2Id}")
    fun getByOauth2Id(@PathVariable oauth2Id: String): ResponseEntity<Student> =
        studentService.getStudentByOauth2Id(oauth2Id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/username/{username}")
    fun getByUsername(@PathVariable username: String): ResponseEntity<Student> =
        studentService.getStudentByUserName(username)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @GetMapping("/email/{email}")
    fun getByEmail(@PathVariable email: String): ResponseEntity<Student> =
        studentService.getStudentByEmail(email)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PostMapping
    fun create(@RequestBody student: Student): ResponseEntity<Student> =
        studentService.createStudent(student)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody student: Student): ResponseEntity<Student> {
        if (studentService.getStudentById(id) == null) return ResponseEntity.notFound().build()
        student.id = id
        return studentService.updateStudent(id, student)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.badRequest().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> =
        if (studentService.deleteStudent(id)) ResponseEntity.noContent().build()
        else ResponseEntity.notFound().build()

    @PostMapping("/{id}/courses")
    fun addCourse(
        @PathVariable id: String,
        @RequestBody payload: CourseGrade
    ): ResponseEntity<Student> =
        studentService.addCourseToStudent(id, payload.courseName, payload.grade)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()

    @PatchMapping("/{id}/courses")
    fun updateGrade(
        @PathVariable id: String,
        @RequestBody payload: CourseGrade
    ): ResponseEntity<Student> =
        try {
            studentService.updateStudentGrade(id, payload.courseName, payload.grade)
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.notFound().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
}
