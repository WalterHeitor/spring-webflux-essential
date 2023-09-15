package com.softwalter.springwebfluxessentials.controller

import com.softwalter.springwebfluxessentials.domain.Anime
import com.softwalter.springwebfluxessentials.service.AnimeService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("animes")
class AnimeController(val animeService: AnimeService) {

    @GetMapping
    fun listAll(): Flux<Anime> {
        return animeService.findAll()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Int): Mono<Anime> {
        return animeService.findById(id)
    }
}