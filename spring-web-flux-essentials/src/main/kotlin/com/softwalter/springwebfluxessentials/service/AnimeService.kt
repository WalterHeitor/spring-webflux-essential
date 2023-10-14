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

    fun save(anime: Anime?): Mono<Anime> {
        return animeRepository.save(anime)
    }
    fun update(anime: Anime): Mono<Void> {
//        return findById(anime.id!!)
//                .map { anime.name = it.name }
//                .flatMap { animeRepository.save(anime) }
//                .thenEmpty(Mono.empty())
        var findById = findById(anime.id!!)
        findById.name(anime.name)
        var flatMap = animeRepository.save(findById.block()).flatMap { findById }
        return  flatMap.thenEmpty(Mono.empty<Void>())
    }
//    fun update(anime: Anime): Mono<Void> {
//        return findById(anime.id!!)
//                .map<Any> { (id): Anime -> anime.withId(id) }
//                .flatMap<Any>(Function<Any, Mono<*>> { entity: Any? -> animeRepository.save(entity) })
//                .thenEmpty(Mono.empty<Void>())
//    }
//fun update(anime: Anime): Mono<Void> {
//    return findById(anime.id!!)
//            .flatMap { animeRepository.save(anime.withId(it)) }
//            .thenEmpty(Mono.empty())
//}

}

