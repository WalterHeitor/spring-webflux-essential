package com.softwalter.springwebfluxessentials.repository

import com.softwalter.springwebfluxessentials.domain.Anime
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface AnimeRepository : ReactiveCrudRepository<Anime, Integer> {

    fun findById(id: Int): Mono<Anime>
}