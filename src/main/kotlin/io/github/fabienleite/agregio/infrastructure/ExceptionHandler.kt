package io.github.fabienleite.agregio.infrastructure

import io.github.fabienleite.agregio.domain.PuissanceNegativeException
import io.github.fabienleite.agregio.domain.TypeNonListeException
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder
import org.jboss.resteasy.reactive.RestResponse.Status
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider

@Provider
class ExceptionHandler: ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception): Response? {
        return when(exception::class.java) {
            PuissanceNegativeException::class.java,
            TypeNonListeException::class.java -> ResponseBuilder
                    .create(Status.BAD_REQUEST, "Un paramètre est incorrect")
                    .build().toResponse()
            else -> ResponseBuilder.create(Status.INTERNAL_SERVER_ERROR, "").build().toResponse()
        }
    }
}