package pe.area51.githubsearcher.ui.search;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.area51.githubsearcher.Application;
import pe.area51.githubsearcher.databinding.ElementGithubProjectBinding;
import pe.area51.githubsearcher.databinding.FragmentSearchBinding;
import pe.area51.githubsearcher.domain.FavoriteDataGateway;
import pe.area51.githubsearcher.domain.GitHubProjectDataGateway;
import pe.area51.githubsearcher.domain.Project;
import pe.area51.githubsearcher.ui.Response;
import pe.area51.githubsearcher.ui.ViewModelFactory;

public class FragmentSearch extends Fragment {

    private ViewModelSearch viewModelSearch;

    private GitHubProjectAdapter gitHubProjectAdapter;

    private FragmentSearchBinding fragmentSearchBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Application application = (Application) context.getApplicationContext();
        final ViewModelFactory viewModelFactory = application.getViewModelFactory();
        viewModelSearch = ViewModelProviders
                .of(this, viewModelFactory)
                .get(ViewModelSearch.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModelSearch.searchProjectsByUserName("");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        final LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());
        fragmentSearchBinding.recyclerView.setLayoutManager(linearLayoutManager);
        gitHubProjectAdapter = new GitHubProjectAdapter(inflater);
        viewModelSearch.getSearchResultResponse().observe(this, this::showResult);
        fragmentSearchBinding.recyclerView.setAdapter(gitHubProjectAdapter);
        return fragmentSearchBinding.getRoot();
    }

    private void showResult(final Response<List<Project>, Void> result) {
        switch (result.status) {
            case IN_PROGRESS:
                fragmentSearchBinding.setShowProgress(true);
                break;
            case SUCCESS:
                fragmentSearchBinding.setShowProgress(false);
                gitHubProjectAdapter.replaceElements(result.successData);
                break;
            case ERROR:
                break;
        }
    }

    private final static class GitHubProjectAdapter extends RecyclerView.Adapter<GitHubProjectAdapter.ViewHolder> {

        private final LayoutInflater layoutInflater;
        private final List<Project> projects;

        public GitHubProjectAdapter(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
            this.projects = new ArrayList<>();
        }

        public final void replaceElements(@NonNull List<Project> projects) {
            this.projects.clear();
            this.projects.addAll(projects);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final ElementGithubProjectBinding elementGithubProjectBinding =
                    ElementGithubProjectBinding.inflate(
                            layoutInflater,
                            parent,
                            false);
            return new ViewHolder(elementGithubProjectBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final Project project = projects.get(position);
            holder.elementGithubProjectBinding.textViewProjectName.setText(project.getName());
        }

        @Override
        public int getItemCount() {
            return projects.size();
        }

        public final static class ViewHolder extends RecyclerView.ViewHolder {

            private final ElementGithubProjectBinding elementGithubProjectBinding;

            public ViewHolder(final ElementGithubProjectBinding elementGithubProjectBinding) {
                super(elementGithubProjectBinding.getRoot());
                this.elementGithubProjectBinding = elementGithubProjectBinding;
            }
        }

    }


}
