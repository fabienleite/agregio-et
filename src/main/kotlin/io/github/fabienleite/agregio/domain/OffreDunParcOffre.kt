package io.github.fabienleite.agregio.domain

import java.math.BigDecimal
import java.time.LocalDate

data class OffreDunParcOffre(
    val puissance: Int,
    val origine: Parc,
    val date: LocalDate,
    val horaire: CreneauHoraire,
    val prixPlancher: BigDecimal
)