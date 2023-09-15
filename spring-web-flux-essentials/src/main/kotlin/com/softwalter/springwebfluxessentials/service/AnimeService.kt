package com.softwalter.springwebfluxessentials.service

import com.softwalter.springwebfluxessentials.domain.Anime
import com.softwalter.springwebfluxessentials.repository.AnimeRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
@Slf4j
@RequiredArgsConstructor
class AnimeService(val animeRepository: AnimeRepository) {
    fun findAll(): Flux<Anime> {
        return animeRepository.findAll();
    }
    fun findById(id: Int): Mono<Anime> {
        return animeRepository.findById(id)
    }
}