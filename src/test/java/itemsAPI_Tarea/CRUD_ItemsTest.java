package itemsAPI_Tarea;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUD_ItemsTest {
    @Test
    public void createUpdateReadDeleteItems() {
        JSONObject bodyProject = new JSONObject();
        bodyProject.put("Content", "Item_PabloTarea");

        // create
        Response response=given()
                .auth()
                .preemptive()
                .basic("pablo@pablo.com", "pablo123")
                .body(bodyProject.toString())
                .log()
                .all().
                when()
                .post("https://todo.ly/api/items.json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyProject.get("Content")));

        int idProject = response.then().extract().path("Id");
        // update
        bodyProject.put("Content", "Update_ItemPabloTarea");
        response=given()
                .auth()
                .preemptive()
                .basic("pablo@pablo.com", "pablo123")
                .body(bodyProject.toString())
                .log()
                .all().
                when()
                .put("https://todo.ly/api/items/"+idProject+".json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyProject.get("Content")));

        // read
        response=given()
                .auth()
                .preemptive()
                .basic("pablo@pablo.com", "pablo123")
                .log()
                .all().
                when()
                .get("https://todo.ly/api/items/"+idProject+".json");


        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyProject.get("Content")));

        // delete
        response=given()
                .auth()
                .preemptive()
                .basic("pablo@pablo.com", "pablo123")
                .log()
                .all().
                when()
                .delete("https://todo.ly/api/items/"+idProject+".json");

        response.then()
                .log()
                .all()
                .statusCode(200)
                .body("Content", equalTo(bodyProject.get("Content")))
                .body("Deleted", equalTo(true));

    }
}
