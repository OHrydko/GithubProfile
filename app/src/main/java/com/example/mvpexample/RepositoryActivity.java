package com.example.mvpexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.adapter.RepositoryAdapter;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends AppCompatActivity {
    private ArrayList<GithubRepository> githubRepositories;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.list)
    TextView list;

    @BindView(R.id.avatar_repo)
    ImageView avatarRepo;

    private RepositoryAdapter repositoryAdapter;

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
        setLanguages(githubRepositories,language);
        initRecyclerView();

        if (githubRepositories.size() != 0) {
            repositoryAdapter = new RepositoryAdapter(githubRepositories, RepositoryActivity.this);
            recyclerView.setAdapter(repositoryAdapter);
            list.setText("List of repository  " + name);
            //user_name.setText(githubs.get(0).getOwner().getLogin());
            Glide.with(RepositoryActivity.this).load(image)
                    .into(avatarRepo);
        } else {
            list.setText("In this User hasn't repositori");
        }
    }
    private void initRecyclerView(){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
    private ArrayList<GithubRepository> setLanguages(ArrayList<GithubRepository> githubRepositories,
                                                    ArrayList<String> arrayList) {
        for (int i = 0;i<githubRepositories.size();i++ ){
            githubRepositories.get(i).setLanguage(arrayList.get(i));
        }
        return githubRepositories;
    }
}
