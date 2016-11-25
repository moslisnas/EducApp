package alejandromoli.educapp;

import android.os.Bundle;

import alejandromoli.educapp.ExternalDB.GeneralHandler;

public class LoginActivity extends BDAccessActivity{

    public GeneralHandler generalHandlerExternalBD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Inicializamos el acceso a la base de datos externa.
        generalHandlerExternalBD = new GeneralHandler(this);
        generalHandlerExternalBD.processServerRequest(GeneralHandler.USERS);
    }

    public void updateUI(){

    }
}