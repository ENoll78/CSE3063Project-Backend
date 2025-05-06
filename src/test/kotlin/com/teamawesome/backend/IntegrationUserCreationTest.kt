package com.teamawesome.backend

import com.teamawesome.backend.models.Admin
import com.teamawesome.backend.models.CourseGrade
import com.teamawesome.backend.models.Faculty
import com.teamawesome.backend.models.Student
import com.teamawesome.backend.services.AdminService
import com.teamawesome.backend.services.FacultyService
import com.teamawesome.backend.services.StudentService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntegrationUserCreationTest @Autowired constructor(
    private val adminService: AdminService,
    private val facultyService: FacultyService,
    private val studentService: StudentService
) {

    @Test
    fun `persist all three user types`() {
        // Create and save an Admin
        val admin = Admin().apply {
            username  = "ratatouilleLover"
            email     = "TofuScramble@nmt.edu"
            title     = "Super Admin"
            firstName = "Remy"
            lastName  = "IChallengeExam"
            password  = "CSLoginIsHard"
            oauth2Id  = "test-admin-oauth-id"
        }
        val savedAdmin = adminService.createAdmin(admin)
        assertNotNull(savedAdmin.id, "Admin should have been assigned an ID")  // :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}

        // Create and save a Faculty
        val faculty = Faculty().apply {
            username  = "Shinobi-wan-kenobi"
            email     = "ShinTacular@nmt.edu"
            oauth2Id  = "test-faculty-oauth-id"
            phoneNumber = "867-5309"
            firstName = "Nun"
            lastName = "Ya"
            password = "123456secure"
            coursesTaught = mutableListOf("CSE 3063", "CSE 4089", "CSE 3026")
            approved  = true
        }
        val savedFaculty = facultyService.createFaculty(faculty)
        assertNotNull(savedFaculty.id, "Faculty should have been assigned an ID")

        // Create and save a Student
        val student = Student().apply {
            username  = "davidplusplus"
            email     = "WebSlinger420@aol.com"
            oauth2Id  = "test-student-oauth-id"
            firstName = "David"
            lastName  = "Catboy"
            password  = "sirswaggums"
            courses   = mutableListOf(
                CourseGrade(courseName = "CSE 3042", grade = 4.0),
                CourseGrade(courseName = "CSE 4023", grade = 3.8)
            )
        }
        val savedStudent = studentService.createStudent(student)!!
        assertNotNull(savedStudent.id, "Student should have been assigned an ID")
    }
}
