package com.teamawesome.backend.models

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

/**
 * Faculty member
 *
 * Has a list of courses they teach, phone number, ???
 */

@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id")
class Faculty : User() {

    @ElementCollection
    var coursesTaught: MutableList<@NotEmpty String> = mutableListOf()

    @NotNull
    @NotEmpty
    @Column(length = 20)
    var phoneNumber: String = ""

    // Admins will be able to toggle this
    @Column(nullable = false)
    var approved: Boolean = false

}