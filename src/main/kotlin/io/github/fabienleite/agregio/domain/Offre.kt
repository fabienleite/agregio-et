package io.github.fabienleite.agregio.domain

import java.time.LocalDate

data class Offre(
    val blocsDOffre: List<BlocDOffre>,
    val marché: Marche,
    val date: LocalDate
) {
    fun prixPlancher() = blocsDOffre.maxOf { bloc -> bloc.prixPlancher() }
}