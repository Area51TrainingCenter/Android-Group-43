package pe.area51.githubsearcher.data.remote_repository;

import android.support.annotation.NonNull;

import java.util.List;

import pe.area51.githubsearcher.data.favorite_repository.RoomDatabase;
import pe.area51.githubsearcher.domain.DataGatewayException;
import pe.area51.githubsearcher.domain.GitHubProject;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;

public class RemoteRepository implements GitHubProjectDataGateway {

    private final RetrofitGitHubClient retrofitGitHubClient;
    private final RoomDatabase roomDatabase;

    public RemoteRepository(RetrofitGitHubClient retrofitGitHubClient, RoomDatabase roomDatabase) {
        this.retrofitGitHubClient = retrofitGitHubClient;
        this.roomDatabase = roomDatabase;
    }

    @NonNull
    @Override
    public List<GitHubProject> findProjectsByProjectName(@NonNull String projectName) throws DataGatewayException {
        return null;
    }

    @NonNull
    @Override
    public List<GitHubProject> findProjectsByUserName(@NonNull String userName) throws DataGatewayException {
        return null;
    }
}
