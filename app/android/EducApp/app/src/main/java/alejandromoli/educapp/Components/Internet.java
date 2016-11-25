package alejandromoli.educapp.Components;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Clase que nos permite realizar operaciones sobre la conexión mediante Wifi ó 3G/4G a Internet.
 * @author Alejandro Molina Quesada.
 * @version 24/11/2016.
 */
public class Internet {
    //Variables.
    private ConnectivityManager connMgr;
    private NetworkInfo networkInfo;
    private final static int REQUEST_ENABLE_WIFI = 1;

    /**
     * Constructor de la clase.
     * @param activity Parámetro con la instancia del "Activity".
     */
    public Internet(AppCompatActivity activity){
        connMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connMgr.getActiveNetworkInfo();
    }
    /**
     * Método que nos permite activar la conexión Wifi.
     * @param activity Parámetro con la instancia del "Activity".
     */
    public void enableInternet(AppCompatActivity activity){
        //Lo activamos solamente si está desactivado.
        if(networkInfo == null || !networkInfo.isConnected()){
            Intent enableWifiIntent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
            activity.startActivityForResult(enableWifiIntent, REQUEST_ENABLE_WIFI);
        }
    }
    /**
     * Método que nos permite saber si el Wifi está activado.
     * @return Booleano true: si está activado, false: en caso contrario.
     */
    public boolean isEnabled(){
        //Actualizamos la información de la conexión.
        networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
