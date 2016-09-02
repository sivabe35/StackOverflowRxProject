package com.gaurav.stackoverflowrxproject.presenters;

import android.util.Log;

import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerItem;
import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerModel;
import com.gaurav.stackoverflowrxproject.network.StackOverflowService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gaurav on 3/9/16.
 */
public class AnswerPresenterImpl implements AnswersPresenter.AnswerPresenterModel {
    AnswersPresenter.AnswerPresenterView mAnswerPresenterView;
    Retrofit mRetrofit;
    StackOverflowService mStackOverflowService;
    List<AnswerItem> mAnswerItemList=new ArrayList<>();
    public AnswerPresenterImpl(AnswersPresenter.AnswerPresenterView answerPresenterView) {
        mAnswerPresenterView = answerPresenterView;
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.stackexchange.com/2.2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mStackOverflowService = mRetrofit.create(StackOverflowService.class);
    }

    @Override
    public void fetchAnswers(int id) {
        mAnswerPresenterView.showLoading();
        mStackOverflowService.getAnswers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendData, throwable -> {
                    Log.d("TAG", throwable.getLocalizedMessage());
                    mAnswerPresenterView.showErrorView();
                });
    }

    private void sendData(AnswerModel model) {
        for(AnswerItem i:model.items) {
            mAnswerItemList.add(i);
        }
        mAnswerPresenterView.showAnswers(mAnswerItemList);
    }
}
