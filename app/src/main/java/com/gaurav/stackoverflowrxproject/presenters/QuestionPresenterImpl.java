package com.gaurav.stackoverflowrxproject.presenters;

import android.util.Log;

import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionModel;
import com.gaurav.stackoverflowrxproject.network.StackOverflowService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by gaurav on 2/9/16.
 */
public class QuestionPresenterImpl implements QuestionPresenter.QuestionPresenterModel {
    List<QuestionItem> questionsTitleList = new ArrayList<>();
    QuestionPresenter.QuestionPresenterView mQuestionPresenterView;
    Retrofit mRetrofit;
    StackOverflowService mStackOverflowService;
    Subscription mSubscription = null;
    int pageNumber = 1;

    public QuestionPresenterImpl(QuestionPresenter.QuestionPresenterView questionPresenterView) {
        mQuestionPresenterView = questionPresenterView;
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.stackexchange.com/2.2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mStackOverflowService = mRetrofit.create(StackOverflowService.class);
    }

    public void fetchData() {
        questionsTitleList.clear();
        Log.d("TAG", "data asked");
        mStackOverflowService.getQuestions(pageNumber, "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::sendData, throwable -> {
                    Log.d("TAG", throwable.getLocalizedMessage());
                    mQuestionPresenterView.showErrorView();
                });
    }

    public void sendData(QuestionModel model) {
        for (QuestionItem i : model.items) {
            questionsTitleList.add(i);
        }
        mQuestionPresenterView.showData(questionsTitleList);
    }

    public void nextPage() {
        pageNumber++;
        fetchData();
    }

    public void prevPage() {
        if (pageNumber > 1) {
            mQuestionPresenterView.showLoading();
            pageNumber--;
            fetchData();
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public List<QuestionItem> getData() {
        return questionsTitleList;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void releaseSubscription() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
