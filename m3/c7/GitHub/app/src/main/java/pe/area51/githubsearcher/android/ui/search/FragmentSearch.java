package pe.area51.githubsearcher.android.ui.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pe.area51.githubsearcher.databinding.ElementGithubProjectBinding;
import pe.area51.githubsearcher.databinding.FragmentSearchBinding;
import pe.area51.githubsearcher.domain.GitHubProject;
import pe.area51.githubsearcher.android.ui.BaseFragment;
import pe.area51.githubsearcher.android.ui.Response;

public class FragmentSearch extends BaseFragment {

    private ViewModelSearch viewModelSearch;

    private GitHubProjectAdapter gitHubProjectAdapter;

    private FragmentSearchBinding fragmentSearchBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        gitHubProjectAdapter = new GitHubProjectAdapter(inflater, this::onItemClick);
        viewModelSearch.getSearchResultResponse().observe(this, this::showResult);
        fragmentSearchBinding.recyclerView.setAdapter(gitHubProjectAdapter);
        fragmentInteractionListener.setToolbar(fragmentSearchBinding.toolbar);
        return fragmentSearchBinding.getRoot();
    }

    private void showResult(final Response<List<GitHubProject>, Void> result) {
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

    private void onItemClick(final GitHubProject gitHubProject) {
        final Uri webUri = Uri.parse(gitHubProject.getWebUrl());
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(webUri);
        startActivity(intent);
    }

    private final static class GitHubProjectAdapter extends RecyclerView.Adapter<GitHubProjectAdapter.ViewHolder> {

        private final LayoutInflater layoutInflater;
        private final List<GitHubProject> gitHubProjects;
        private final OnItemClickListener onItemClickListener;

        public GitHubProjectAdapter(LayoutInflater layoutInflater,
                                    OnItemClickListener onItemClickListener) {
            this.layoutInflater = layoutInflater;
            this.gitHubProjects = new ArrayList<>();
            this.onItemClickListener = onItemClickListener;
        }

        public final void replaceElements(@NonNull List<GitHubProject> gitHubProjects) {
            this.gitHubProjects.clear();
            this.gitHubProjects.addAll(gitHubProjects);
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
            return new ViewHolder(elementGithubProjectBinding, onItemClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            final GitHubProject gitHubProject = gitHubProjects.get(position);
            holder.elementGithubProjectBinding.textViewProjectName.setText(gitHubProject.getName());
            holder.gitHubProject = gitHubProject;
        }

        @Override
        public int getItemCount() {
            return gitHubProjects.size();
        }

        public final static class ViewHolder extends RecyclerView.ViewHolder {

            private final ElementGithubProjectBinding elementGithubProjectBinding;
            private GitHubProject gitHubProject;

            public ViewHolder(final ElementGithubProjectBinding elementGithubProjectBinding,
                              final OnItemClickListener onItemClickListener) {
                super(elementGithubProjectBinding.getRoot());
                this.elementGithubProjectBinding = elementGithubProjectBinding;
                this.elementGithubProjectBinding.getRoot().setOnClickListener(
                        v -> onItemClickListener.onItemClick(gitHubProject)
                );
            }
        }

        public interface OnItemClickListener {

            void onItemClick(final GitHubProject gitHubProject);

        }

    }


}
