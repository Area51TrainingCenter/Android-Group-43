package pe.area51.githubsearcher.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pe.area51.githubsearcher.domain.DataGatewayException;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.domain.Project;

public class FakeDatabase implements FavoriteDataGateway, GitHubProjectDataGateway {

    private List<Project> testProjects;

    public String[] projectNames = new String[]{
            "Fake Library!",
            "HTTP Client",
            "Another Fake Library",
            "Mock Client",
            "Awesome Graph!",
            "Lite MOD Tracker"
    };

    public String[] projectDescriptions = new String[]{
            "An awesome software library.",
            "This is an awesome software project.",
            "The best project of all time!"
    };

    public String[] userNames = new String[]{
            "User1",
            "User2",
            "User3",
            "User4"
    };

    public String[] projectUrls = new String[]{
            "http://www.example.com"
    };

    public FakeDatabase(final int size) {
        testProjects = generateData(size);
    }

    private List<Project> generateData(final int size) {
        final List<Project> testProjects = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < size; i++) {
            final int randomInt = (int) (1000 * random.nextFloat());
            final String id = String.valueOf(i + 1);
            final String name = projectNames[randomInt % projectNames.length];
            final String description = projectDescriptions[randomInt % projectDescriptions.length];
            final String webUrl = projectUrls[randomInt % projectUrls.length];
            final String userName = userNames[randomInt % userNames.length];
            final long creationTimestamp = System.currentTimeMillis();
            final long lastUpdateTimestamp = System.currentTimeMillis();
            final int popularity = 100;
            final boolean isFavorite = false;
            final long favoriteTimestamp = -1;
            testProjects.add(new Project(
                    id,
                    name,
                    description,
                    webUrl,
                    userName,
                    creationTimestamp,
                    lastUpdateTimestamp,
                    popularity,
                    isFavorite,
                    favoriteTimestamp
            ));
        }
        return testProjects;
    }

    @NonNull
    @Override
    public List<Project> getAllFavoriteProjects() throws DataGatewayException {
        throw new DataGatewayException("Unimplemented method!");
    }

    @Override
    public boolean addFavorite(@NonNull String projectId) throws DataGatewayException {
        throw new DataGatewayException("Unimplemented method!");
    }

    @Override
    public boolean removeFavorite(@NonNull String projectId) throws DataGatewayException {
        throw new DataGatewayException("Unimplemented method!");
    }

    @NonNull
    @Override
    public List<Project> findProjectsByProjectName(@NonNull String projectName) throws DataGatewayException {
        return testProjects;
    }

    @NonNull
    @Override
    public List<Project> findProjectsByUserName(@NonNull String userName) throws DataGatewayException {
        return testProjects;
    }
}
