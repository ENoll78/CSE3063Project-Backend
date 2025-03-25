package com.teamawesome.backend.models

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

/**
 * User abstract class
 *
 * Important to note that Kotlin files are inherently "final", and can't be inherited.
 * The @MapperSuperClass overrides this, mutable var, null safety, etc
 *
 */

// MappedSuperClass indicates this class's properties will be inherited by @entity classes (Admin, Faculty, Student)
// This class itself is not mapped to its own table (Database)
@MappedSuperclass
abstract class User {

    // @Id sets this as the primary key (Database)
    // The value is automatically generated, UUID is a 128-bit number/36-char string
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null

    // Non-null field
    // @Column maps this field to a column of "length" maximum characters, and "unique" means the entry
    // will be just that. We can also use "name = " to set a custom column name in the DB. "nullable" accepts nulls
    // This can be used for MFA services (Google Auth, GitHub, etc.)
    @NotNull
    @Column(length = 256, unique = true)
    var oauth2Id: String = ""

    // Note for Erik - @Bean for the password hash, @Service for registration class
    @NotNull
    @Column(length = 64)
    var password: String = ""

    // @Email checks for valid email address (e.g. @.com, .edu, etc)
    @NotNull
    @Email
    @Column(length = 1024)
    var email: String = ""

    // @NotEmpty self explanatory
    @NotNull
    @NotEmpty
    @Column(length = 128, unique = true)
    var username: String = ""

    @NotNull
    @Column(length = 128)
    var firstName: String = ""

    @NotNull
    @Column(length = 128)
    var lastName: String = ""
}