package io.github.fabienleite.agregio.use_case

import io.github.fabienleite.agregio.domain.Offre
import io.github.fabienleite.agregio.domain.OffreDunParcRepository
import java.time.LocalDate

class CreerOffre(val offreDunParcRepository: OffreDunParcRepository) {

    fun execute(puissanceVoulue: Int, marché: String, date: LocalDate, créneauHoraire: String): Offre {
        TODO()
    }
}