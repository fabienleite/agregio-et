package io.github.fabienleite.agregio.use_case

import io.github.fabienleite.agregio.domain.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.*

class CréerParcTest : StringSpec({

    val parcRepository = mockk<ParcRepository>()
    val creerParc = CreerParc(parcRepository)

    "devrait créer un parc s'il est configuré avec toutes les données" {
        // Given
        val puissanceMax = 2000
        val typeSolaire = "SOLAIRE"

        val parcSauvegardé = slot<Parc>()
        every { parcRepository.sauvegarder(parc = capture(parcSauvegardé)) } just runs

        // When
        val uuidCréé = creerParc.execute(typeSolaire, puissanceMax)

        // Then
        verify(exactly = 1) { parcRepository.sauvegarder(any()) }
        uuidCréé shouldBe parcSauvegardé.captured.id
        parcSauvegardé.captured.type shouldBe TypeDeParc.SOLAIRE
        parcSauvegardé.captured.puissanceMaximaleEnMW shouldBe 2000
    }

    "devrait renvoyer une exception si la puissance est négative" {
        // Given
        val puissanceMaxNégative = -1000
        val typeSolaire = "SOLAIRE"

        // When, Then
        shouldThrow<PuissanceNegativeException> {
            creerParc.execute(typeSolaire, puissanceMaxNégative)
        }
    }

    "devrait renvoyer une exception si la le type de parc n'existe pas" {
        // Given
        val puissanceMaxNégative = 1000
        val typeSolaire = "HÉLIUM"

        // When, Then
        shouldThrow<TypeNonListeException> {
            creerParc.execute(typeSolaire, puissanceMaxNégative)
        }
    }
})