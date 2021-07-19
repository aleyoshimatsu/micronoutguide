package dev.alexandreyoshimatsu.example.micronaut.repository

import dev.alexandreyoshimatsu.example.micronaut.domain.Genre
import dev.alexandreyoshimatsu.example.micronaut.domain.SortingAndOrderArguments
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


interface GenreRepository {

    fun findById(@NotNull id: Long): Genre

    fun save(@NotBlank name: String): Genre

    fun saveWithException(@NotBlank name: String): Genre

    fun deleteById(@NotNull id: Long)

    fun findAll(@NotNull args: SortingAndOrderArguments): List<Genre>

    fun update(@NotNull id: Long, @NotBlank name: String): Int

}