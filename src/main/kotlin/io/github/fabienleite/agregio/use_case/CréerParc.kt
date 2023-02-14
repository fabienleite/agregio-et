package io.github.fabienleite.agregio.use_case

import io.github.fabienleite.agregio.domain.Parc
import io.github.fabienleite.agregio.domain.ParcRepository
import java.util.UUID

class CréerParc (val parcRepository: ParcRepository) {
    fun execute(type: String, puissanceMaximale: Int): UUID {
        val parc = Parc(type, puissanceMaximale)
        parcRepository.sauvegarder(parc)
        return parc.id
    }
}