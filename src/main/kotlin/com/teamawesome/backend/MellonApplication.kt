package com.teamawesome.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MellonApplication

fun main(args: Array<String>) {
	runApplication<MellonApplication>(*args)
}
