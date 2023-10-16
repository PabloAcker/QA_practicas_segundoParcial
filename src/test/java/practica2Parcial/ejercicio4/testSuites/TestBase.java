package practica2Parcial.ejercicio4.testSuites;

import practica2Parcial.ejercicio4.factoryRequest.RequestInfo;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
public class TestBase {
    public String post ="post";
    public String put = "put";
    public static String get ="get";
    public String delete ="delete";

    public static RequestInfo requestInfo;
    public static Response response;

    @BeforeEach
    public void before(){
        requestInfo = new RequestInfo();
    }
}
