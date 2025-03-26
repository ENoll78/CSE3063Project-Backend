package com.teamawesome.backend.models

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty

/**
 *
 * Student user, extends User fields
 * Stores a list of courses with their current grades, compute overall GPA?
 *
 */

@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id")
class Student : User() {

    /**
     * A list of course records for this student, each containing a course name and grade.
     * This is read-only for students (enforced at the service/controller layer),
     * but faculty can modify grades for their classes.
     */
    @ElementCollection
    var courses: MutableList<CourseGrade> = mutableListOf()

    val gpa: Double
        get() = if (courses.isEmpty()) 0.0 else courses.map {it.grade}.average()
}

/**
 * Embeddable type for a single course and its grade
 */
@Embeddable
data class CourseGrade(
    // Name of a course
    @NotEmpty
    var courseName: String = "",

    // Grade
    @Min(0)
    @Max(4)
    var grade: Double = 0.0,
)