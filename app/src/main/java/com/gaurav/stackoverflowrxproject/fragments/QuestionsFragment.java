package com.gaurav.stackoverflowrxproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gaurav.stackoverflowrxproject.R;
import com.gaurav.stackoverflowrxproject.activity.MainActivity;
import com.gaurav.stackoverflowrxproject.adapters.QuestionsRecyclerViewAdapter;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;
import com.gaurav.stackoverflowrxproject.presenters.QuestionPresenter;
import com.gaurav.stackoverflowrxproject.presenters.QuestionPresenterImpl;
import com.gaurav.stackoverflowrxproject.utils.ItemClickSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by gaurav on 2/9/16.
 */
public class QuestionsFragment extends Fragment implements QuestionPresenter.QuestionPresenterView {

    @BindView(R.id.retryButton)
    Button retryButton;
    @BindView(R.id.prevPageButton)
    Button prevPageButton;
    @BindView(R.id.nextPageButton)
    Button nextPageButton;
    @BindView(R.id.pageNumberView)
    TextView pageNumberView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.dataView)
    View dataView;
    @BindView(R.id.questionsRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorView)
    View errorView;
    QuestionsRecyclerViewAdapter mQuestionsRecyclerViewAdapter;
    QuestionPresenterImpl mQuestionPresenter;
    Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        showLoading();
        mQuestionPresenter = new QuestionPresenterImpl(this);
        if (((MainActivity) getActivity()).getQuestionModelList() != null) {
            mQuestionPresenter.setPageNumber(((MainActivity) getActivity()).getPagenumber());
            showData(((MainActivity) getActivity()).getQuestionModelList());
        } else {
            mQuestionPresenter.fetchData();
        }
    }


    @OnClick(R.id.retryButton)
    public void retryButtonClick(View view) {
        showLoading();
        mQuestionPresenter.fetchData();
    }

    @OnClick(R.id.nextPageButton)
    public void nextPageButtonClick(View v) {
        showLoading();
        mQuestionPresenter.nextPage();
    }

    @OnClick(R.id.prevPageButton)
    public void prevPageButtonClick(View v) {
        mQuestionPresenter.prevPage();
    }


    @Override
    public void showData(List<QuestionItem> questionList) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mQuestionsRecyclerViewAdapter = new QuestionsRecyclerViewAdapter(questionList, getActivity());
        mRecyclerView.setAdapter(mQuestionsRecyclerViewAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener((recyclerView, position, v) -> {
            ((MainActivity) getActivity()).setPagenumber(mQuestionPresenter.getPageNumber());
            ((MainActivity) getActivity()).setQuestionModelList(mQuestionPresenter.getData());
            AnswersFragment fragment = new AnswersFragment();
            fragment.setQuestionItem(mQuestionPresenter.getData().get(position));
            ((MainActivity) getActivity()).changeFragment(fragment);
        });
        dataView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        pageNumberView.setText("Page No:" + mQuestionPresenter.getPageNumber());
    }

    @Override
    public void showErrorView() {
        dataView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        dataView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mQuestionPresenter.releaseSubscription();
    }
}
