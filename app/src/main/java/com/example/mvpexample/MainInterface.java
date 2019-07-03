package com.example.mvpexample;

import com.example.mvpexample.model.Github;
import com.example.mvpexample.model.GithubRepository;

import java.util.ArrayList;

public interface MainInterface {

    interface Model {
        void load(CallBackGithub callBackGithub, String userName);

        void loadRepository(CallBackRepository callBackRepository, String userName);
    }

    interface View {
        void showUser(Github github);

        void showRepository(ArrayList<GithubRepository> githubRepository);
    }

    interface Presenter {
        void onButtonWasClicked(String userName);

        void sendUserRepository(String userName);

        void onDestroy();
    }
}
