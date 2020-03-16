package com.example.mvpexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.adapter.RepositoryAdapter;
import com.example.mvpexample.callback.onItemClick;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends AppCompatActivity implements onItemClick {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list)
    TextView list;

    @BindView(R.id.avatar_repo)
    ImageView avatarRepo;

    private ArrayList<GithubRepository> githubRepositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        githubRepositories = intent.getParcelableArrayListExtra("data");
        String image = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        ArrayList<String> language = intent.getStringArrayListExtra("language");
        setLanguages(githubRepositories, language);
        initRecyclerView();

        if (githubRepositories.size() != 0) {
            RepositoryAdapter repositoryAdapter = new RepositoryAdapter(githubRepositories, this);
            recyclerView.setAdapter(repositoryAdapter);
            list.setText(String.format("List of repository  %s", name));

            Glide.with(RepositoryActivity.this).load(image)
                    .into(avatarRepo);
        } else {
            list.setText(getResources().getString(R.string.repository));
        }
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setLanguages(ArrayList<GithubRepository> githubRepositories,
                              ArrayList<String> arrayList) {
        for (int i = 0; i < githubRepositories.size(); i++) {
            githubRepositories.get(i).setLanguage(arrayList.get(i));
        }
    }

    @Override
    public void click(int position) {
        Uri uri = Uri.parse(githubRepositories.get(position).getHtmlUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
