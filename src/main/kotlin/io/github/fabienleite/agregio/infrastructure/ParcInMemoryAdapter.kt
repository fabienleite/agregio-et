package io.github.fabienleite.agregio.infrastructure

import io.github.fabienleite.agregio.domain.Parc
import io.github.fabienleite.agregio.domain.ParcRepository
import java.util.LinkedList
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ParcInMemoryAdapter: ParcRepository {
    val parcs = LinkedList<Parc>()

    override fun sauvegarder(parc: Parc) {
        parcs.add(parc)
    }
}