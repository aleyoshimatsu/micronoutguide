package dev.alexandreyoshimatsu.example.micronaut

import io.micronaut.runtime.Micronaut.*

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("dev.alexandreyoshimatsu")
        .start()
}