package com.softwalter.springwebfluxessentials.adapters.persistence.database

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
@Table("anime")
data class AnimeEntity(
        @Id
        val id: Int? = null,

        @NotNull
        @NotEmpty(message = "The name of this anime cannot be empty")
        var name: String? = null
)
