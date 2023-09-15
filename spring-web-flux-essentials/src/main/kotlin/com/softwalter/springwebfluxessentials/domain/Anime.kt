package com.softwalter.springwebfluxessentials.domain

import jakarta.validation.constraints.NotEmpty
import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("anime")
class Anime {
    @Id
    private val id: Int? = null

    @NotEmpty(message = "The name of this anime cannot be empty")
    private val name: String? = null
}