package com.softwalter.springwebfluxessentials.application.domain


import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@Value
data class Anime (
        val id: Int? = null,

        @NotNull
        @NotEmpty(message = "The name of this anime cannot be empty")
        var name: String? = null
)