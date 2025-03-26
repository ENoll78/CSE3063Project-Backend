package com.teamawesome.backend.services

import com.teamawesome.backend.models.Student
import com.teamawesome.backend.models.CourseGrade
import com.teamawesome.backend.repositories.StudentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StudentService(private val studentRepository: StudentRepository) {

    fun getStudentById(id: String): Student? {
        return studentRepository.findByIdOrNull(id)
    }

    fun getStudentByOauth2Id(oauth2Id: String): Student? {
        return studentRepository.findDistinctByOauth2Id(oauth2Id)
    }

    fun getStudentByUserName(userName: String): Student? {
        return  studentRepository.findDistinctByUsername(userName)
    }

    fun getStudentByEmail(email: String): Student? {
        return studentRepository.findDistinctByEmail(email)
    }

    fun createStudent(student: Student): Student? {
        student.id = null
        return studentRepository.save(student)
    }

    fun updateStudent(id: String, student: Student): Student? {
        return studentRepository.save(student)
    }

    fun addCourseToStudent(studentId: String, courseName: String, grade: Double): Student? {
        val existing = studentRepository.findByIdOrNull(studentId) ?: return null

        // Construct a new CourseGrade object
        val newCourse = CourseGrade(courseName = courseName, grade = grade)
        existing.courses.add(newCourse)

        return studentRepository.save(existing)
    }

    fun deleteStudent(id: String): Boolean {
        return if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id)
            true
        } else false
    }

    // Update student's grade for a specific course
    // Finds course in student's courses list, updates the grade, persists the change
    fun updateStudentGrade(studentId: String, courseName: String, newGrade: Double): Student? {
        val student = studentRepository.findByIdOrNull(studentId)
            ?: return null
        val course = student.courses.find { it.courseName == courseName }
            ?: throw IllegalArgumentException("Student is not enrolled in $courseName")
        course.grade = newGrade
        return  studentRepository.save(student)
    }
}