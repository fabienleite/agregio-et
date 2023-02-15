package io.github.fabienleite.agregio.infrastructure

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.equalTo
import org.jboss.resteasy.reactive.RestResponse.StatusCode
import org.junit.jupiter.api.Test

@QuarkusTest
class ParcControllerTest {

    @Test
    fun test_200_quand_tous_les_paramètres_sont_corrects() {
        given()
            .contentType(ContentType.JSON)
            .body(
                """
                    {
                      "type": "EOLIEN",
                      "puissanceMaximale": 1200
                    }
                """.trimIndent()
            )
        .`when`()
            .post("/parc")
        .then()
            .statusCode(StatusCode.OK)
    }

    @Test
    fun test_400_quand_un_paramètre_n_est_pas_correct() {
        given()
            .contentType(ContentType.JSON)
            .body(
                """
                    {
                      "type": "EOLIEN",
                      "puissanceMaximale": -2
                    }
                """.trimIndent()
            )
        .`when`()
            .post("/parc")
        .then()
            .statusCode(StatusCode.BAD_REQUEST)
            .body(equalTo("Un paramètre est incorrect"))
    }
}