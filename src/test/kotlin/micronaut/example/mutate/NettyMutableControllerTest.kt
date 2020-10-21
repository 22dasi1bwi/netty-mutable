package micronaut.example.mutate

import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import javax.inject.Inject

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NettyMutableControllerTest {

    @Inject
    lateinit var server: EmbeddedServer

    @Test
    fun `succeeds on mutating NettyRequest twice`() {
        RestAssured.requestSpecification = RequestSpecBuilder().setBaseUri(server.url.toString()).build()

        RestAssured.given()
                .request()
                .get("/sample/mutatetwice")
                .then()
                .statusCode(HttpStatus.OK.code)
    }

    @Test
    fun `succeeds on mutating NettyRequest only once`() {
        RestAssured.requestSpecification = RequestSpecBuilder().setBaseUri(server.url.toString()).build()

        RestAssured.given()
                .request()
                .get("/sample/mutateonce")
                .then()
                .statusCode(HttpStatus.OK.code)
    }

    @Test
    fun `returns correct URI when modifying twice` () {
        RestAssured.requestSpecification = RequestSpecBuilder().setBaseUri(server.url.toString()).build()

        RestAssured.given()
                .request()
                .get("/sample/mutateUriTwice")
                .then()
                .statusCode(HttpStatus.OK.code)
                .body(Matchers.endsWith("first-mutate/second-mutate"))
    }
}
