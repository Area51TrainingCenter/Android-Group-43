package pe.area51.githubsearcher.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.ui.search.ViewModelSearch;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final GitHubProjectDataGateway gitHubProjectDataGateway;
    private final FavoriteDataGateway favoriteDataGateway;

    public ViewModelFactory(GitHubProjectDataGateway gitHubProjectDataGateway, FavoriteDataGateway favoriteDataGateway) {
        this.gitHubProjectDataGateway = gitHubProjectDataGateway;
        this.favoriteDataGateway = favoriteDataGateway;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ViewModelSearch.class)) {
            return (T) new ViewModelSearch(gitHubProjectDataGateway, favoriteDataGateway);
        }
        try {
            return modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown ViewModel class!");
        }
    }
}
