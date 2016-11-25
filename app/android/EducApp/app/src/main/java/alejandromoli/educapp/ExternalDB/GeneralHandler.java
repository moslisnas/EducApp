package alejandromoli.educapp.ExternalDB;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;

import alejandromoli.educapp.BDAccessActivity;
import alejandromoli.educapp.Components.Internet;

/**
 * Clase que implementa una hebra y que nos facilita realizar peticiones a un repositorio, WebService o base de datos externa.
 * @author Alejandro Molina Quesada.
 * @version 24/11/2016.
 * @see AsyncTask
 */

public class GeneralHandler extends AsyncTask<Void, Void, Void> {
    public final static String URL = "http://192.168.1.33:8000/";//ESTA ES LA IP DEL EQUIPO, DE LA NETWORK, EN EL QUE SE EJECUTA EL SERVIDOR (reemplazar por la url con nombre de dominio).
    public final static String USERS = "USERS";
    //Variables.
    private BDAccessActivity myActivity;
    public boolean loading;
    public String request;
    public JSONArray users;

    //Constructores.
    /**
     * Constructor de la clase.
     * @param pContext Parámetro del "Context" dónde se utiliza esta clase.
     */
    public GeneralHandler(BDAccessActivity pContext){
        super();
        this.myActivity = pContext;
        this.loading = false;
    }

    //Otros métodos.
    /**
     * Método que realiza las peticiones pertinentes al repositorio o base de datos externa.
     * @param pRequest Tipo de petición realizada.
     */
    public void processServerRequest(String pRequest){
        Internet internet = new Internet(myActivity);
        if(internet.isEnabled()) {
            this.loading = true;
            this.request = pRequest;
            this.execute();
        }
        else{
            Toast.makeText(myActivity.getApplicationContext(), "Active la conexión Wifi o el 3G/4G.", Toast.LENGTH_LONG).show();
            internet.enableInternet(myActivity);
            while(!loading){
                if(internet.isEnabled()) {
                    this.loading = true;
                    this.request = pRequest;
                    this.execute();
                }
            }
        }
    }

    //Métodos del AsyncTask.
    @Override
    protected Void doInBackground(Void... params) {
        if(this.request != null) {
            switch (this.request){
                case USERS:
                    users = new UserHandler().loadUsers();
                    break;
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        this.loading = false;
        myActivity.updateUI();
        super.onPostExecute(result);
    }

}