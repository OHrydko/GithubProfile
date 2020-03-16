package com.example.mvpexample.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.mvpexample.R;
import com.example.mvpexample.model.Github;
import com.example.mvpexample.repository.RepositoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements MainInterface.View {

    private MainInterface.Presenter presenter;

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

    @BindView(R.id.containerLouder)
    ConstraintLayout loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new Presenter(this);

        find.setOnClickListener(v ->
                presenter.onButtonWasClicked(inputText.getText().toString()));

        constraintLayout.setOnClickListener(v -> startActivity(new Intent(this, RepositoryActivity.class)
                .putExtra("name", inputText.getText().toString())));

    }

    @Override
    public void showUser(Github github) {
        constraintLayout.setVisibility(View.VISIBLE);
        name.setText(github.getName());
        error.setVisibility(View.INVISIBLE);
        name.setText(github.getLogin());

        if (github.getLocation() != null) {

            Glide.with(MainActivity.this).load(R.drawable.ic_location_on_black_24dp)
                    .into(ic_location);

        }
        location.setText((github.getLocation() != null) ? github.getLocation() : "");
        ic_location.setVisibility((github.getLocation() != null) ? View.VISIBLE : View.INVISIBLE);

        Glide.with(MainActivity.this).load(github.getAvatar_url()).into(avatar);

    }

    @Override
    public void showError(String errors) {
        error.setText(errors);
    }

    @Override
    public void showLoading() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loader.setVisibility(View.GONE);

    }


}
