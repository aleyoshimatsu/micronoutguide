package dev.alexandreyoshimatsu.example.micronaut.repository

import dev.alexandreyoshimatsu.example.micronaut.config.ApplicationConfiguration
import dev.alexandreyoshimatsu.example.micronaut.domain.Genre
import dev.alexandreyoshimatsu.example.micronaut.domain.SortingAndOrderArguments
import io.micronaut.transaction.annotation.ReadOnly
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceException
import javax.transaction.Transactional

@Singleton
open class GenreRepositoryImpl(
    private val entityManager: EntityManager,
    private val applicationConfiguration: ApplicationConfiguration
) : GenreRepository {

    companion object {
        private val VALID_PROPERTY_NAMES: List<String> = arrayOf("id", "name").asList()
    }

    @ReadOnly
    open override fun findById(id: Long): Genre {
        return entityManager.find(Genre::class.java, id)
    }

    @Transactional
    open override fun save(name: String): Genre {
        val genre = Genre(name)
        entityManager.persist(genre)
        return genre
    }

//    @Transactional
    open override fun saveWithException(name: String): Genre {
        save(name)
        throw PersistenceException()
    }

//    @Transactional
    open override fun deleteById(id: Long) {
        val genre = findById(id)
        entityManager.remove(genre)
    }

    @ReadOnly
    open override fun findAll(args: SortingAndOrderArguments): List<Genre> {
        var qlString = "SELECT g FROM Genre as g"
        if (args.sort != null && args.order != null && VALID_PROPERTY_NAMES.contains(args.sort)) {
            qlString += " ORDER BY g." + args.sort + " " + args.order.toLowerCase()
        }
        val query = entityManager.createQuery(qlString, Genre::class.java)
        query.maxResults = args.max ?: applicationConfiguration.getMax()
        if (args.offset != null) {
            query.firstResult = args.offset
        }

        return query.resultList
    }

//    @Transactional
    open override fun update(id: Long, name: String): Int {
        return entityManager.createQuery("UPDATE Genre g SET name = :name where id = :id")
            .setParameter("name", name)
            .setParameter("id", id)
            .executeUpdate()
    }

}