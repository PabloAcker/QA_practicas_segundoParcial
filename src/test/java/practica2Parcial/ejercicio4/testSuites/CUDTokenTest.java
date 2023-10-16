package practica2Parcial.ejercicio4.testSuites;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import practica2Parcial.ejercicio4.configuration.Configuration;
import practica2Parcial.ejercicio4.factoryRequest.FactoryRequest;
import practica2Parcial.ejercicio4.factoryRequest.RequestInfo;

import static org.hamcrest.Matchers.equalTo;
public class CUDTokenTest extends TestBase{
    @BeforeAll
    public static void setup() {
        requestInfo = new RequestInfo();
        requestInfo.setUrl(Configuration.host + "/api/authentication/token.json");
        response = FactoryRequest.make(get).send(requestInfo);
        Configuration.token = response.then().extract().path("TokenString");
        System.out.println(Configuration.token);
    }

    @Test
    public void createUpdateDeleteProject() {
        JSONObject body = new JSONObject();
        body.put("Content", "Nuevo proyecto con token");

        this.createProject(Configuration.host + "/api/projects.json", body, post);
        int idProject = response.then().extract().path("Id");

        body.put("Content", "Nuevo proyecto con token update");
        this.updateProject(Configuration.host + "/api/projects/" + idProject + ".json", body, put);
        this.deleteProject(idProject, delete, body);

    }

    private void deleteProject(int idProject, String delete, JSONObject body) {
        requestInfo.setUrl(Configuration.host + "/api/projects/" + idProject + ".json");
        response = FactoryRequest.make(delete).sendWithToken(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }

    private void updateProject(String host, JSONObject body, String put) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make(put).sendWithToken(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }

    private void createProject(String host, JSONObject body, String post) {
        requestInfo.setUrl(host)
                .setBody(body.toString());
        response = FactoryRequest.make(post).sendWithToken(requestInfo);
        response.then().statusCode(200).
                body("Content", equalTo(body.get("Content")));
    }
}
