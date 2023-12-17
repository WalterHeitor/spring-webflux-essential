package com.softwalter.springwebfluxessentials.adapters.web

import com.softwalter.springwebfluxessentials.application.domain.Anime
import com.softwalter.springwebfluxessentials.application.usecases.AnimeService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid


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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid  anime: Anime?): Mono<Anime> {
        return animeService.save(anime)
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@RequestBody @Valid  anime: Anime?): Mono<Void> {
        return animeService.update(anime!!)
    }
}