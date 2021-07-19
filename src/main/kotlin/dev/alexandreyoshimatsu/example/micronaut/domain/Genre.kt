package dev.alexandreyoshimatsu.example.micronaut.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "genre")
class Genre(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    val books: Set<Book> = HashSet()

) {

    constructor(name: String) : this(0, name)

}