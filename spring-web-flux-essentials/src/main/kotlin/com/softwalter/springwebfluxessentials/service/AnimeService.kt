package com.softwalter.springwebfluxessentials.service

import com.softwalter.springwebfluxessentials.domain.Anime
import com.softwalter.springwebfluxessentials.repository.AnimeRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Slf4j
@RequiredArgsConstructor
class AnimeService(val animeRepository: AnimeRepository) {
    fun findAll(): Flux<Anime> {
        return animeRepository.findAll().log()
    }
    fun findById(id: Int): Mono<Anime> {
        return animeRepository.findById(id)
                .switchIfEmpty(monoResponseStatusNotFoundException())
                .log()
    }

    private fun monoResponseStatusNotFoundException(): Mono<Anime> =
            Mono.error((ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not found")))
}