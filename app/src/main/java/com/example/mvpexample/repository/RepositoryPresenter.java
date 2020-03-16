package com.example.mvpexample.repository;

import com.example.mvpexample.App;
import com.example.mvpexample.callback.CallBackRepository;
import com.example.mvpexample.db.Dao;
import com.example.mvpexample.model.GithubRepository;
import com.example.mvpexample.network.Repository;

import java.util.List;

public class RepositoryPresenter implements RepositoryInterface.Presenter {

    private Repository repository;
    private RepositoryInterface.View mView;
    private Dao dao;

    RepositoryPresenter(RepositoryInterface.View mView) {
        this.mView = mView;
        this.repository = new Repository();
        dao = App.getComponent().getDao();
    }

    @Override
    public void onButtonWasClicked(String userName) {
        mView.showLoading();
        if (dao.checkLoginExistsRepos(userName) == 0) {
            repository.loadRepository(new CallBackRepository() {
                @Override
                public void result(List<GithubRepository> githubRepository) {
                    mView.hideLoading();
                    mView.showRepository(githubRepository);
                    new Thread(() -> {
                        for (GithubRepository row: githubRepository) {
                            row.setLogin(userName);
                        }
                        dao.insertRepos(githubRepository);
                    }).run();
                }

                @Override
                public void error(String message) {
                    mView.hideLoading();
                    mView.showError(message);
                }
            }, userName);
        } else {
            mView.hideLoading();
            mView.showRepository(dao.getRepos(userName));
        }
    }

}
