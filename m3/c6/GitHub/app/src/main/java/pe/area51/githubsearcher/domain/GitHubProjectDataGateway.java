package pe.area51.githubsearcher.domain;

import android.support.annotation.NonNull;

import java.util.List;

public interface GitHubProjectDataGateway {

    @NonNull
    List<Project> findProjectsByProjectName(@NonNull final String projectName) throws DataGatewayException;

    @NonNull
    List<Project> findProjectsByUserName(@NonNull final String userName) throws DataGatewayException;

}
