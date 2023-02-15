package io.github.fabienleite.agregio.infrastructure

import com.fasterxml.jackson.annotation.JsonProperty
import io.github.fabienleite.agregio.use_case.CreerParc
import java.util.UUID
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path("/parc")
class ParcController(val creerParc: CreerParc) {

    data class CreerParcRessource(
        @field:JsonProperty("type") val type: String,
        @field:JsonProperty("puissanceMaximale") val puissanceMaximale: Int,
    )

    @POST
    @Produces("text/plain")
    fun creer(creerParcRessource: CreerParcRessource): UUID {
        val parcId = creerParc.execute(
            creerParcRessource.type,
            creerParcRessource.puissanceMaximale,
        )
        return parcId
    }
}