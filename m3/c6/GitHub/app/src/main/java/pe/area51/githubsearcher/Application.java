package pe.area51.githubsearcher;

import pe.area51.githubsearcher.data.FakeDatabase;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.ui.ViewModelFactory;

public class Application extends android.app.Application {

    private GitHubProjectDataGateway gitHubProjectDataGateway;
    private FavoriteDataGateway favoriteDataGateway;
    private ViewModelFactory viewModelFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        final FakeDatabase fakeDatabase = new FakeDatabase(20);
        gitHubProjectDataGateway = fakeDatabase;
        favoriteDataGateway = fakeDatabase;
        viewModelFactory = new ViewModelFactory(gitHubProjectDataGateway, favoriteDataGateway);
    }

    public FavoriteDataGateway getFavoriteDataGateway() {
        return favoriteDataGateway;
    }

    public GitHubProjectDataGateway getGitHubProjectDataGateway() {
        return gitHubProjectDataGateway;
    }

    public ViewModelFactory getViewModelFactory() {
        return viewModelFactory;
    }
}
