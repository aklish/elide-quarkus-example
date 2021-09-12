package example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ElideEndpointTests {

    @Test
    public void jsonApiEndpointTest() {
        given()
          .when().get("/jsonapi/book")
          .then()
             .statusCode(200);
    }

    @Test
    public void swaggerApiEndpointTest() {
        given()
                .when().get("/doc/api")
                .then()
                .log().all()
                .statusCode(200);
    }
}