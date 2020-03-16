package com.example.mvpexample.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpexample.R;
import com.example.mvpexample.callback.onItemClick;
import com.example.mvpexample.model.GithubRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private List<GithubRepository> githubArrayList;
    private onItemClick onItemClick;

    public RepositoryAdapter(List<GithubRepository> githubArrayList,
                             onItemClick onItemClick) {
        this.githubArrayList = githubArrayList;
        this.onItemClick = onItemClick;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepository github = githubArrayList.get(position);
        holder.repoName.setText(github.getName());

        holder.language.setText(github.getLanguage() != null ?
                github.getLanguage() : "");
        holder.square.setVisibility(github.getLanguage() != null ? View.VISIBLE : View.GONE);

        holder.cardView.setOnClickListener(view -> onItemClick.click(github.getHtml_url(), github.getName()));

    }

    @Override
    public int getItemCount() {
        return githubArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.repo_name)
        TextView repoName;
        @BindView(R.id.language)
        TextView language;
        @BindView(R.id.square)
        ImageView square;
        @BindView(R.id.card)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
