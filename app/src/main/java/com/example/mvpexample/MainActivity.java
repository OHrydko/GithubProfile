package com.example.mvpexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainInterface.View {

    MainInterface.Presenter presenter;
    String image;

    @BindView(R.id.find)
    Button find;

    @BindView(R.id.inputText)
    TextView inputText;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.location)
    TextView location;

    @BindView(R.id.ic_location)
    ImageView ic_location;

    @BindView(R.id.error)
    TextView error;

    @BindView(R.id.avatar)
    CircleImageView avatar;

    @BindView(R.id.linearLayoutClick)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new Presenter(this);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonWasClicked(inputText.getText().toString());
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendUserRepository(inputText.getText().toString());
            }
        });


    }

    @Override
    public void showUser(Github github) {
        linearLayout.setVisibility(View.VISIBLE);
        name.setText(github.getName());
        error.setVisibility(View.INVISIBLE);
        name.setText(github.getLogin());
        image = github.getAvatarUrl();
        if (github.getLocation() != null) {

            ic_location.setVisibility(View.VISIBLE);
            Glide.with(MainActivity.this).load(R.drawable.ic_location_on_black_24dp)
                    .into(ic_location);
            location.setText(String.valueOf(github.getLocation()));

        } else {
            ic_location.setVisibility(View.INVISIBLE);
            location.setText(String.valueOf(" "));
        }

        Glide.with(MainActivity.this).load(github.getAvatarUrl()).into(avatar);

    }

    @Override
    public void showRepository(ArrayList<GithubRepository> githubRepository) {
        Intent intent = new Intent(this, RepositoryActivity.class);
        intent.putParcelableArrayListExtra("data", githubRepository);
        intent.putExtra("image", image);
        intent.putExtra("name", inputText.getText().toString());
        intent.putStringArrayListExtra("language",getLanguage(githubRepository));
        startActivity(intent);
    }

    public ArrayList<String> getLanguage(ArrayList<GithubRepository> githubRepositories) {
        ArrayList<String> language = new ArrayList<>();
        for (int i = 0; i < githubRepositories.size(); i++) {
            language.add((String) githubRepositories.get(i).getLanguage());
        }
        return language;
    }
}
