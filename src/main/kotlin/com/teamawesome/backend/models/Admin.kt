package com.teamawesome.backend.models

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.validation.constraints.NotNull

/**
 * Admin user
 *
 * Most all functionality will be handled in service layer
 */
@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator::class,
    property = "id")
class Admin : User() {

    // Some random title
    @NotNull
    @Column(length = 128)
    var title: String = ""

}