package alejandromoli.educapp.ExternalDB;

import org.json.JSONArray;

/**
 * Created by Alejandro on 24/11/2016.
 */

public class UserHandler {
    //Variables.
    private final String GET_PATH = GeneralHandler.URL + "user/api";

    public JSONArray loadUsers(){
        JSONArray result = null;

        String fullPath = GET_PATH;
        JSONArray jsonArray;

        //Llamamos a la tarea que realiza las peticiones.
        jsonArray = RequestsHandler.getJsonArray(fullPath);
        //POR HACER --> CARGAR LOS MODELOS
        result = jsonArray;

        return result;
    }
}
