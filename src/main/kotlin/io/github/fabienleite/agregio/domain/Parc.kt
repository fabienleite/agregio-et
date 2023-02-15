package io.github.fabienleite.agregio.domain

import java.util.*

class Parc(
    type: String,
    puissanceMaximaleEnMW: Int
) {
    val id: UUID = UUID.randomUUID()

    val type: TypeDeParc = try {
        TypeDeParc.valueOf(type)
    } catch (exception: IllegalArgumentException) {
        throw TypeNonListeException()
    }

    val puissanceMaximaleEnMW: Int = when (puissanceMaximaleEnMW > 0) {
        true -> puissanceMaximaleEnMW
        false -> throw PuissanceNegativeException()
    }
}