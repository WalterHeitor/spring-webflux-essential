package com.softwalter.springwebfluxessentials.domain

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Table("anime")
data class Anime (
        @Id
        val id: Int? = null,

        @NotNull
        @NotEmpty(message = "The name of this anime cannot be empty")
        val name: String? = null
)