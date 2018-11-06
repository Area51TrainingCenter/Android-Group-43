package pe.area51.githubsearcher.android;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import pe.area51.githubsearcher.R;
import pe.area51.githubsearcher.android.ui.FragmentInteractionListener;
import pe.area51.githubsearcher.android.ui.search.FragmentSearch;

public class ActivityMain extends AppCompatActivity implements FragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            final FragmentSearch fragmentSearch = new FragmentSearch();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragmentSearch)
                    .commit();
        }
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }
}
