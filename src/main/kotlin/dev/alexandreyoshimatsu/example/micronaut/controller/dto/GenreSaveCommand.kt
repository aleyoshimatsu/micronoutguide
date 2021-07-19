package dev.alexandreyoshimatsu.example.micronaut.controller.dto

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
class GenreSaveCommand(

    @NotBlank
    val name: String

)