package pe.area51.githubsearcher;

import java.util.List;

public interface GitHubProjectDataGateway {

    List<Project> findProjectsByProjectName(final String projectName);

    List<Project> findProjectsByUserName(final String userName);

}
