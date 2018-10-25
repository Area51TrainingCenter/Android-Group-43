package pe.area51.githubsearcher;

public class Application extends android.app.Application {

    private GitHubProjectDataGateway gitHubProjectDataGateway;
    private FavoriteDataGateway favoriteDataGateway;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
