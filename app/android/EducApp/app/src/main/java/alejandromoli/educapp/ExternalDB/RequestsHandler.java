package alejandromoli.educapp.ExternalDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Alejandro on 24/11/2016.
 */

public class RequestsHandler {
    /**
     * Método que nos devuelve un objeto "JSON" a partir de una URL del WebService: <h>http://aventuragraficageolocalizada.esy.es/</h>.
     * @param pPath Parámetro con la URL de petición al WebService.
     * @return El objeto "JSON" solicitado.
     */
    public static JSONObject getJson(String pPath){
        JSONObject result;

        //Obtenemos la cadena Json del servidor y lo convertimos en un JSONObject.
        String stringJson = getJsonStringFromServer(pPath);
        result = convertToJsonObject(stringJson);

        return result;
    }
    /**
     * Método que nos devuelve un array "JSON" a partir de una URL del WebService: <h>http://aventuragraficageolocalizada.esy.es/</h>.
     * @param pPath Parámetro con la URL de petición al WebService.
     * @return El array "JSON" solicitado.
     */
    public static JSONArray getJsonArray(String pPath){
        JSONArray result = null;

        //Obtenemos la cadena Json del servidor y lo convertimos en un JSONArray.
        String stringJson = getJsonStringFromServer(pPath);
        result = convertToJsonArray(stringJson);

        return result;
    }
    /**
     * Método que convierte una cadena en formato "JSON" en un objeto "JSON" de Java.
     * @param pRespuesta Parámetro con la cadena en formato "JSON".
     * @return El objeto "JSON" de Java.
     */
    public static JSONObject convertToJsonObject(String pRespuesta){
        JSONObject result = null;
        try {
            result = new JSONObject(pRespuesta);
        }
        catch (JSONException e) {
            e.printStackTrace();
            System.out.println("JSON Exception con el string " + pRespuesta);
        }

        return result;
    }
    /**
     * Método que convierte una cadena en formato "JSON" en un array "JSON" de Java.
     * @param pRespuesta Parámetro con la cadena en formato "JSON".
     * @return El array "JSON" de Java.
     */
    public static JSONArray convertToJsonArray(String pRespuesta){
        JSONArray result = null;
        try {
            result = new JSONArray(pRespuesta);
        }
        catch (JSONException e) {
            e.printStackTrace();
            System.out.println("IntroActivity: JSON Array Exception con el string " + pRespuesta);
            result = new JSONArray();
        }
        return result;
    }

    //Peticiones Http.
    /**
     * Método que realiza una petición Http al WebService: <h>http://aventuragraficageolocalizada.esy.es/</h>, y nos devuelve una cadena en formato "JSON".
     * @param pFullPath Parámetro con la URL de petición al WebService.
     * @return La cadena en formato "JSON".
     * @throws IOException en caso de error en la lectura de resultados del WebService.
     */
    public static String getJsonStringFromServer(String pFullPath){
        String result = null;
        URL url;
        HttpURLConnection urlConnection;

        try {
            //Abrimos la conexión con el WebService.
            url = new URL(pFullPath);
            urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                //Mientras haya contenido en la respuesta del WebService la vamos insertando en un "StringBuilder".
                while ((line = br.readLine()) != null)
                    sb.append(line+"\n");
                br.close();
                result = sb.toString();
            }
            finally {
                //Cerramos la conexión con el WebService.
                urlConnection.disconnect();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}