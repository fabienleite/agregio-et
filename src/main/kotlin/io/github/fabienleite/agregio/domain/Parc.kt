package io.github.fabienleite.agregio.domain

import java.util.*

class Parc(
    type: String,
    puissanceMaximale: Int
) {
    val id: UUID = UUID.randomUUID()

    val type: TypeDeParc = try {
        TypeDeParc.valueOf(type)
    } catch (exception: IllegalArgumentException) {
        throw TypeNonListéException()
    }

    val puissanceMaximaleEnMW: Int = when (puissanceMaximale > 0) {
        true -> puissanceMaximale
        false -> throw PuissanceNégativeException()
    }
}