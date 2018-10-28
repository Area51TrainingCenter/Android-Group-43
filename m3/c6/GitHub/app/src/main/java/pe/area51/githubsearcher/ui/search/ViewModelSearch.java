package pe.area51.githubsearcher.ui.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import pe.area51.githubsearcher.domain.DataGatewayException;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.domain.Project;
import pe.area51.githubsearcher.ui.Response;

public class ViewModelSearch extends ViewModel {

    private final GitHubProjectDataGateway gitHubProjectDataGateway;
    private final FavoriteDataGateway favoriteDataGateway;

    private final MutableLiveData<Response<List<Project>, Void>> searchResultResponse;

    public ViewModelSearch(GitHubProjectDataGateway gitHubProjectDataGateway, FavoriteDataGateway favoriteDataGateway) {
        this.gitHubProjectDataGateway = gitHubProjectDataGateway;
        this.favoriteDataGateway = favoriteDataGateway;
        searchResultResponse = new MutableLiveData<>();
    }

    public void searchProjectsByUserName(final String userName) {
        //setValue() se utiliza si se llama desde el Main Thread.
        //postValue() si es desde otros hilos.
        searchResultResponse.setValue(
                new Response<>(Response.Status.IN_PROGRESS, null, null)
        );
        AsyncTask.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                final List<Project> projects = gitHubProjectDataGateway.findProjectsByUserName(userName);
                searchResultResponse.postValue(
                        new Response<>(Response.Status.SUCCESS, projects, null)
                );
            } catch (DataGatewayException e) {
                e.printStackTrace();
                searchResultResponse.postValue(
                        new Response<>(Response.Status.ERROR, null, null)
                );
            }
        });
    }

    public void searchProjectsByName(final String projectName) {
        /*AsyncTask.execute(() -> {
            final List<Project> projects =
                    gitHubProjectDataGateway.findProjectsByProjectName(projectName);
            searchProjectsByNameResponse.postValue(projects);
        });*/
    }

    public LiveData<Response<List<Project>, Void>> getSearchResultResponse() {
        return searchResultResponse;
    }
}
