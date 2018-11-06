package pe.area51.githubsearcher.android.ui.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import pe.area51.githubsearcher.domain.DataGatewayException;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.domain.GitHubProject;
import pe.area51.githubsearcher.android.ui.Response;

public class ViewModelSearch extends ViewModel {

    private final GitHubProjectDataGateway gitHubProjectDataGateway;
    private final FavoriteDataGateway favoriteDataGateway;

    private final MutableLiveData<Response<List<GitHubProject>, Void>> searchResultResponse;

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
                final List<GitHubProject> gitHubProjects = gitHubProjectDataGateway.findProjectsByUserName(userName);
                searchResultResponse.postValue(
                        new Response<>(Response.Status.SUCCESS, gitHubProjects, null)
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
            final List<GitHubProject> projects =
                    gitHubProjectDataGateway.findProjectsByProjectName(projectName);
            searchProjectsByNameResponse.postValue(projects);
        });*/
    }

    public LiveData<Response<List<GitHubProject>, Void>> getSearchResultResponse() {
        return searchResultResponse;
    }
}
