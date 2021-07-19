package dev.alexandreyoshimatsu.example.micronaut.controller.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
class GenreUpdateCommand(

    @NotNull
    val id: Long,

    @NotBlank
    val name: String

)