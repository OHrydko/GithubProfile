package com.example.mvpexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainInterface.View {

    private MainInterface.Presenter presenter;
    private String image;

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
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new Presenter(this);

        find.setOnClickListener(v ->
                presenter.onButtonWasClicked(inputText.getText().toString()));

        constraintLayout.setOnClickListener(v ->
                presenter.sendUserRepository(inputText.getText().toString()));

    }

    @Override
    public void showUser(Github github) {
        constraintLayout.setVisibility(View.VISIBLE);
        name.setText(github.getName());
        error.setVisibility(View.INVISIBLE);
        name.setText(github.getLogin());
        image = github.getAvatarUrl();

        if (github.getLocation() != null) {

            Glide.with(MainActivity.this).load(R.drawable.ic_location_on_black_24dp)
                    .into(ic_location);

        }
        location.setText((github.getLocation() != null) ? String.valueOf(github.getLocation()) : "");
        ic_location.setVisibility((github.getLocation() != null) ? View.VISIBLE : View.INVISIBLE);

        Glide.with(MainActivity.this).load(github.getAvatarUrl()).into(avatar);

    }

    @Override
    public void showError(String errors) {
        error.setText(errors);
    }

    @Override
    public void showRepository(ArrayList<GithubRepository> githubRepository) {
        Intent intent = new Intent(this, RepositoryActivity.class);
        intent.putParcelableArrayListExtra("data", githubRepository);
        intent.putExtra("image", image);
        intent.putExtra("name", inputText.getText().toString());
        intent.putStringArrayListExtra("language", getLanguage(githubRepository));
        startActivity(intent);
    }

    private ArrayList<String> getLanguage(ArrayList<GithubRepository> githubRepositories) {
        ArrayList<String> language = new ArrayList<>();
        for (int i = 0; i < githubRepositories.size(); i++) {
            language.add((String) githubRepositories.get(i).getLanguage());
        }
        return language;
    }
}
