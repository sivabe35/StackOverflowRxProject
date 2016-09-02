package com.gaurav.stackoverflowrxproject.presenters;

import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;

import java.util.List;

/**
 * Created by gaurav on 2/9/16.
 */
public interface QuestionPresenter {
    public interface QuestionPresenterView {
        void showData(List<QuestionItem> questionTitleList);
        void showErrorView();
        void showLoading();
    }
    public interface QuestionPresenterModel {
        List<QuestionItem> getData();
    }
}
