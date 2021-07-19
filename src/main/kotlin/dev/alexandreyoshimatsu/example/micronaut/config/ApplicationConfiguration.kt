package dev.alexandreyoshimatsu.example.micronaut.config

import javax.validation.constraints.NotNull

interface ApplicationConfiguration {

    @NotNull
    fun getMax(): Int

}