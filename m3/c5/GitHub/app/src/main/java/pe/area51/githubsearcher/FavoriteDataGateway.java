package pe.area51.githubsearcher;

import java.util.List;

public interface FavoriteDataGateway {

    List<Project> getAllFavoriteProjects();

    boolean addFavorite(String projectId);

    boolean removeFavorite(String projectId);
}
