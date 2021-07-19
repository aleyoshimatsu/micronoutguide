package dev.alexandreyoshimatsu.example.micronaut.domain

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.Nullable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Introspected
class SortingAndOrderArguments(

    @Nullable
    @PositiveOrZero
    val offset: Int?,

    @Nullable
    @Positive
    val max: Int?,

    @Nullable
    @Pattern(regexp = "id|name")
    val sort: String?,

    @Pattern(regexp = "asc|ASC|desc|DESC")
    @Nullable
    val order: String?

)