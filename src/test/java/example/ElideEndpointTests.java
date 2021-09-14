package example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static com.yahoo.elide.Elide.JSONAPI_CONTENT_TYPE;
import static com.yahoo.elide.test.graphql.GraphQLDSL.document;
import static com.yahoo.elide.test.graphql.GraphQLDSL.field;
import static com.yahoo.elide.test.graphql.GraphQLDSL.selection;
import static com.yahoo.elide.test.graphql.GraphQLDSL.selections;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.attr;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.attributes;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.data;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.id;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.resource;
import static com.yahoo.elide.test.jsonapi.JsonApiDSL.type;
import static io.restassured.RestAssured.given;

import javax.ws.rs.core.MediaType;

@QuarkusTest
public class ElideEndpointTests {

    @Test
    public void testBookJsonApiEndpoint() {
        given()
                .contentType(JSONAPI_CONTENT_TYPE)
                .accept(JSONAPI_CONTENT_TYPE)
                .body(
                        data(
                                resource(
                                        type("book"),
                                        id(1),
                                        attributes(
                                                attr("title", "foo")
                                        )
                                )
                        )
                )
                .post("/book")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
        RestAssured.when().get("/jsonapi/book").then().log().all().statusCode(200);
    }

    @Test
    public void testBookGraphqlEndpoint() {
        String query = document(
                selection(
                        field(
                                "book",
                                selections(
                                        field("id"),
                                        field("title")
                                )
                        )
                )
        ).toQuery();

        String wrapped = String.format("{ \"query\" : \"%s\" }", query);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(wrapped)
                .post("/graphql")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
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