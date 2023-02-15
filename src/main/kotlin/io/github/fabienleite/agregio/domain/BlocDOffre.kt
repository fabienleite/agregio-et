package io.github.fabienleite.agregio.domain

import java.math.BigDecimal
import java.time.LocalDate

class BlocDOffre(
    puissanceDemandee: Int,
    créneauHoraire: CreneauHoraire,
    listeDOffresPossibles: List<OffreDunParcOffre>,
) {
    val créneauHoraire = créneauHoraire
    val composition: List<OffreDunParcVente> = listeDOffresPossibles
        .map{ offre -> OffreDunParcVente(offre) } // TODO: sélectionner les offres

    fun puissance(): Int {
        return composition
            .map{ offre -> offre.puissance }
            .reduce { acc, puissance -> acc + puissance }
    }

    fun prixPlancher () = composition.maxOf { offre -> offre.prixPlancher }

    class OffreDunParcVente(offreDunParc: OffreDunParcOffre) {
        val puissance: Int = offreDunParc.puissance
        val origine: Parc = offreDunParc.origine
        val prixPlancher: BigDecimal = offreDunParc.prixPlancher
    }
}
