package com.softwalter.springwebfluxessentials.controller

import com.softwalter.springwebfluxessentials.domain.Anime
import com.softwalter.springwebfluxessentials.repository.AnimeRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("animes")
class AnimeController {

    private val animeRepository: AnimeRepository? = null

    @GetMapping
    fun listAll(): Flux<Anime> {
        return animeRepository!!.findAll()
    }
}