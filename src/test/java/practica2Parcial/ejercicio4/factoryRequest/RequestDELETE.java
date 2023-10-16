package practica2Parcial.ejercicio4.factoryRequest;

import io.restassured.response.Response;
import practica2Parcial.ejercicio4.configuration.Configuration;

import static io.restassured.RestAssured.given;
public class RequestDELETE implements IRequest{
    @Override
    public Response send(RequestInfo requestInfo) {
        Response response = given()
                .auth()
                .preemptive()
                .basic(Configuration.user, Configuration.password)
                .log()
                .all().
                when()
                .delete(requestInfo.getUrl());
        response.then().log().all();
        return response;
    }

    @Override
    public Response sendWithToken(RequestInfo requestInfo) {
        Response response = given()
                .header("Token", Configuration.token) // Add the token
                .log()
                .all()
                .when()
                .delete(requestInfo.getUrl());

        response.then().log().all();
        return response;
    }
}
