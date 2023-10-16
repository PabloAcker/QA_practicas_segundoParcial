package practica2Parcial.ejercicio4.factoryRequest;

import java.util.HashMap;
import java.util.Map;

public class FactoryRequest {
    public static IRequest make(String type){
        Map<String,IRequest> requestMap = new HashMap<>();
        requestMap.put("put",new RequestPUT());
        requestMap.put("post",new RequestPOST());
        requestMap.put("delete",new RequestDELETE());

        return requestMap.containsKey(type.toLowerCase())?
                requestMap.get(type.toLowerCase()):
                requestMap.get("put");
    }
}
