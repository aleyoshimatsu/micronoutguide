package dev.alexandreyoshimatsu.example.micronaut.controller

import dev.alexandreyoshimatsu.example.micronaut.controller.dto.GenreSaveCommand
import dev.alexandreyoshimatsu.example.micronaut.controller.dto.GenreUpdateCommand
import dev.alexandreyoshimatsu.example.micronaut.domain.Genre
import dev.alexandreyoshimatsu.example.micronaut.repository.GenreRepository
import dev.alexandreyoshimatsu.example.micronaut.domain.SortingAndOrderArguments
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import java.net.URI
import javax.persistence.PersistenceException
import javax.validation.Valid

@ExecuteOn(TaskExecutors.IO)
@Controller("/genres")
open class GenreController(private val genreRepository: GenreRepository) {

    @Get("/{id}")
    fun show(id: Long): Genre {
        return genreRepository.findById(id)
    }

    @Put
    open fun update(@Body @Valid command: GenreUpdateCommand): HttpResponse<Genre> {
        val numberOfEntitiesUpdated: Int = genreRepository.update(command.id, command.name)
        return HttpResponse.noContent<Genre>()
            .header(HttpHeaders.LOCATION, location(command.id).path)
    }

    @Get(value = "/list{?args*}")
    open fun list(@Valid args: SortingAndOrderArguments): List<Genre> {
        return genreRepository.findAll(args)
    }

    @Post
    open fun save(@Body @Valid cmd: GenreSaveCommand): HttpResponse<Genre> {
        val genre: Genre = genreRepository.save(cmd.name)

        return HttpResponse
            .created(genre)
            .headers { headers -> headers.location(location(genre.id)) }
    }

    @Post("/ex")
    open fun saveExceptions(@Body @Valid cmd: GenreSaveCommand): HttpResponse<Genre> {
        try {
            val genre: Genre = genreRepository.saveWithException(cmd.name)

            return HttpResponse
                .created(genre)
                .headers { headers -> headers.location(location(genre.id)) }
        } catch (e: PersistenceException) {
            return HttpResponse.noContent()
        }
    }

    @Delete("/{id}")
    fun delete(id: Long): HttpResponse<Genre> {
        genreRepository.deleteById(id)
        return HttpResponse.noContent()
    }

    private fun location(id: Long): URI {
        return URI.create("/genres/$id")
    }

    private fun location(genre: Genre): URI {
        return URI.create("/genres/${genre.id}")
    }

}