package com.example.mvpexample.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvpexample.R;
import com.example.mvpexample.model.GithubRepository;


import java.util.ArrayList;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<GithubRepository> githubArrayList;

    public RepositoryAdapter(ArrayList<GithubRepository> githubArrayList, Context context) {
        this.githubArrayList = githubArrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepository github = githubArrayList.get(position);
        holder.bind(github);
    }

    @Override
    public int getItemCount() {
        return githubArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView repo_name,language;
        private ImageView square;
        public ViewHolder(View view) {
            super(view);

            repo_name = (TextView)view.findViewById(R.id.repo_name);

            language = (TextView)view.findViewById(R.id.language);
            square = (ImageView)view.findViewById(R.id.square);
            view.setOnClickListener(this::onClick);

        }

        public void bind(GithubRepository githubRepositori){
            repo_name.setText(githubRepositori.getName());

            if(githubRepositori.getLanguage()!=null){
                square.setVisibility(View.VISIBLE);
                language.setText(String.valueOf(githubRepositori.getLanguage()));
            }else{
                square.setVisibility(View.INVISIBLE);
                language.setText(" ");
            }

        }


        @Override
        public void onClick(View v) {
            Uri uri = Uri.parse(githubArrayList.get(getAdapterPosition()).getHtmlUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(intent);
        }
    }
}
