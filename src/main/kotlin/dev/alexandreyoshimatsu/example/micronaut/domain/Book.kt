package dev.alexandreyoshimatsu.example.micronaut.domain

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "book")
class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @NotNull
    @Column(name = "isbn", nullable = false, unique = true)
    val isbn: String,

    @ManyToOne
    val genre: Genre

)