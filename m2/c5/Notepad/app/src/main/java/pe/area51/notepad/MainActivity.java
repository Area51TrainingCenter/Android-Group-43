package pe.area51.notepad;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragmentInteractionInterface {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            /*
            Creamos los fragments e iniciamos la transacción cuando este Activity se cree
            por primera vez.
             */
            final FragmentList fragmentList = new FragmentList();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragmentList)
                    .commit();
        }
        /*
        Si el Activity tiene un estado anterior, no es necesario crear los fragments o ejecutar nuevamente
        la transacción, puesto que en el proceso de recreación de estados, se recrea también el estado
        del FragmentManager.
        */
    }

    @Override
    public void showNote(String id) {
        final FragmentContent fragmentContent = FragmentContent.newInstance(id);
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragmentContent)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setToolbarTitle(String title) {
        setTitle(title);
    }

    @Override
    public void finishFragment() {
        onBackPressed();
    }
}
