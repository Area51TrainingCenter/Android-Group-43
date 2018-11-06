package pe.area51.githubsearcher.data.favorite_repository;

import android.support.annotation.NonNull;

import java.util.List;

import pe.area51.githubsearcher.data.remote_repository.RetrofitGitHubClient;
import pe.area51.githubsearcher.domain.DataGatewayException;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProject;

public class FavoriteRepository implements FavoriteDataGateway {

    private final RetrofitGitHubClient retrofitGitHubClient;
    private final RoomDatabase roomDatabase;

    public FavoriteRepository(RetrofitGitHubClient retrofitGitHubClient, RoomDatabase roomDatabase) {
        this.retrofitGitHubClient = retrofitGitHubClient;
        this.roomDatabase = roomDatabase;
    }

    @NonNull
    @Override
    public List<GitHubProject> getAllFavoriteProjects() throws DataGatewayException {
        return null;
    }

    @Override
    public boolean addFavorite(@NonNull String projectId) throws DataGatewayException {
        return false;
    }

    @Override
    public boolean removeFavorite(@NonNull String projectId) throws DataGatewayException {
        return false;
    }
}
