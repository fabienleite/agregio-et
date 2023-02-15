package io.github.fabienleite.agregio.use_case

import io.github.fabienleite.agregio.domain.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import java.time.LocalDate


class CreerOffreTest: StringSpec({
    val offreDunParcRepository = mockk<OffreDunParcRepository>()
    val créerOffre = CreerOffre(offreDunParcRepository)

    "devrait créer une offre pour le bon créneau et marché en remplissant la puissance disponible" {
        // Given
        val puissanceVoulue = 2000
        val marché = "RÉSERVE_PRIMAIRE"
        val le22Juin2023 = LocalDate.of(2023, 6, 22)
        val créneau = "DE15A18h"

        val prixMinimumLePlusHaut = BigDecimal.valueOf(123.5145.toLong(), 4)
        val parc1 = Parc("SOLAIRE", 500)
        val parc2 = Parc("EOLIEN", 1800)
        val offreParc1 = OffreDunParcOffre(400, parc1, le22Juin2023, CreneauHoraire.DE15A18h, prixMinimumLePlusHaut)
        val offreParc2 = OffreDunParcOffre(1700, parc2, le22Juin2023, CreneauHoraire.DE15A18h, prixMinimumLePlusHaut.minus(BigDecimal.TEN))

        every {
            offreDunParcRepository.récupérerParDateEtHeure(le22Juin2023, CreneauHoraire.DE15A18h)
        } returns listOf(offreParc1, offreParc2)

        // When
        val offre = créerOffre.execute(puissanceVoulue, marché, le22Juin2023, créneau)

        // Then
        offre.marché shouldBe Marche.RÉSERVE_PRIMAIRE
        offre.prixPlancher() shouldBe prixMinimumLePlusHaut
        offre.blocsDOffre.size shouldBe 2
        offre.blocsDOffre[0].puissance() shouldBe 400
        offre.blocsDOffre[1].puissance() shouldBe 1600
    }
})