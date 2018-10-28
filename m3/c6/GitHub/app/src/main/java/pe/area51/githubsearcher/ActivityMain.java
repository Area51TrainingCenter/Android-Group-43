package pe.area51.githubsearcher;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.area51.githubsearcher.ui.search.FragmentSearch;

public class ActivityMain extends AppCompatActivity {

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
}
