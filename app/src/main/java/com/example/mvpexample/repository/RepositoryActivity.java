package com.example.mvpexample.repository;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.R;
import com.example.mvpexample.WebViewActivity;
import com.example.mvpexample.adapter.RepositoryAdapter;
import com.example.mvpexample.callback.onItemClick;
import com.example.mvpexample.model.GithubRepository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends AppCompatActivity implements onItemClick,
        RepositoryInterface.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list)
    TextView list;

    @BindView(R.id.avatar_repo)
    ImageView avatarRepo;

    @BindView(R.id.containerLouder)
    ConstraintLayout loader;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        RepositoryInterface.Presenter presenter = new RepositoryPresenter(this);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");

        presenter.onButtonWasClicked(name);
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void click(String url, String name) {
        startActivity(new Intent(this, WebViewActivity.class)
                .putExtra("url", url)
                .putExtra("title", name));
    }

    @Override
    public void showError(String errors) {

    }

    @Override
    public void showLoading() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void showRepository(List<GithubRepository> githubRepository) {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(githubRepository, this);
        recyclerView.setAdapter(repositoryAdapter);
        list.setText(String.format("List of repository  %s", name));

        if (githubRepository.get(0).getOwner().getAvatar_url() != null)
            Glide.with(RepositoryActivity.this).load(githubRepository.get(0)
                    .getOwner().getAvatar_url())
                    .into(avatarRepo);
    }
}
