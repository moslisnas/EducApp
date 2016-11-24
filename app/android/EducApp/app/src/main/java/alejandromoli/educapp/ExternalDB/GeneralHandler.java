package alejandromoli.educapp.ExternalDB;

import android.os.AsyncTask;

/**
 * Created by Alejandro on 24/11/2016.
 */

public class GeneralHandler extends AsyncTask<Void, Void, Void> {
    //Métodos del AsyncTask.
    @Override
    protected Void doInBackground(Void... params) {
        if(this.request != null) {
            switch (this.request){
                case ALL_ADVENTURES:
                    adventures = new AdventureHandler().loadAllAdventures();
                    break;
                case FULL_ADVENTURE:
                    //Cargamos la aventura completa (cargar también narrativa, episodio y prueba seleccionados).
                    adventureSelected = new AdventureHandler().loadAdventureById(dialogActivityContext.idSelectedAdventure);

                    break;
                case FULL_TEST:
                    testSelected = new TestHandler().loadFullTest(testSelected);
                    break;
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        System.out.println("IntroActivity: ONPOST");
        switch (this.request) {
            case ALL_ADVENTURES:
                adventureSelectorActivityContext.updateUI();
                break;
            case FULL_ADVENTURE:
                dialogActivityContext.updateVariablesWithReceivedData(adventureSelected);
                dialogActivityContext.updateUI();
                dialogActivityContext.updateNextIntent();
                break;
            case FULL_TEST:
                generalFragment.updateVariablesWithReceivedData(testSelected);
                generalFragment.updateUI();
                break;
        }
        this.loading = false;
        super.onPostExecute(result);
    }

}