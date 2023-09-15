package com.softwalter.springwebfluxessentials

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound


@SpringBootApplication
class SpringWebFluxEssentialsApplication

fun main(args: Array<String>) {
BlockHound.install()
	runApplication<SpringWebFluxEssentialsApplication>(*args)
}
