package pe.area51.githubsearcher;

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

import pe.area51.githubsearcher.databinding.ElementGithubProjectBinding;
import pe.area51.githubsearcher.databinding.FragmentSearchBinding;

public class FragmentSearch extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final FragmentSearchBinding fragmentSearchBinding =
                FragmentSearchBinding.inflate(inflater, container, false);
        final LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext());
        fragmentSearchBinding.recyclerView.setLayoutManager(linearLayoutManager);
        return fragmentSearchBinding.getRoot();
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
