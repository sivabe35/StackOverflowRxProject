package com.gaurav.stackoverflowrxproject.presenters;

import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerItem;

import java.util.List;

/**
 * Created by gaurav on 2/9/16.
 */
public interface AnswersPresenter {
    public interface AnswerPresenterView {
        void showAnswers(List<AnswerItem> list);
        void showErrorView();
        void showLoading();
    }
    public interface AnswerPresenterModel {
        void fetchAnswers(int id);
    }
}
