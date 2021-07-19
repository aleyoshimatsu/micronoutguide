package dev.alexandreyoshimatsu.example.micronaut.config

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.core.annotation.NonNull

@ConfigurationProperties("application")
class ApplicationConfigurationProperties: ApplicationConfiguration {

    companion object {
        private val DEFAULT_MAX: Int = 10
    }

    private var max: Int = DEFAULT_MAX

    override fun getMax(): Int {
        return max
    }

    fun setMax(@NonNull max: Int) {
        this.max = max
    }

}