package io.github.fabienleite.agregio.infrastructure

import io.github.fabienleite.agregio.domain.Parc
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class ParcInMemoryRepositoryTest: StringSpec({
    val parcInMemoryAdapter = ParcInMemoryAdapter()
    "sauvegarder devrait ajouter un parc à la liste des parcs" {
        // Given
        val parc = Parc("SOLAIRE", 1234)

        // When
        parcInMemoryAdapter.sauvegarder(parc)

        // Then
        parcInMemoryAdapter.parcs shouldHaveSize 1
        parcInMemoryAdapter.parcs[0] shouldBe parc
    }
})