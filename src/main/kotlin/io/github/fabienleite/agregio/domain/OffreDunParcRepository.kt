package io.github.fabienleite.agregio.domain

import java.time.LocalDate

interface OffreDunParcRepository {
    fun récupérerParDateEtHeure(date: LocalDate, créneauHoraire: CreneauHoraire): List<OffreDunParcOffre>
}