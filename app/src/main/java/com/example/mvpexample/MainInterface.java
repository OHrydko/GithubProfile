package com.example.mvpexample;

import org.reactivestreams.Subscription;

public interface MainInterface {

    interface Model {
        void load(CallBackGithub callBackGithub);
    }

    interface View {
        void showText(String message);

    }

    interface Presenter {
        void onButtonWasClicked();

        void onDestroy();
    }
}
