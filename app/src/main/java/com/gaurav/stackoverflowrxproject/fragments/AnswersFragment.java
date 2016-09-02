package com.gaurav.stackoverflowrxproject.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gaurav.stackoverflowrxproject.R;
import com.gaurav.stackoverflowrxproject.adapters.AnswersRecyclerViewAdapter;
import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerItem;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;
import com.gaurav.stackoverflowrxproject.presenters.AnswerPresenterImpl;
import com.gaurav.stackoverflowrxproject.presenters.AnswersPresenter;
import com.mukesh.MarkdownView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by gaurav on 2/9/16.
 */
public class AnswersFragment extends Fragment implements AnswersPresenter.AnswerPresenterView {

    @BindView(R.id.retryButton)
    Button retryButton;
    @BindView(R.id.errorView)
    View errorView;
    @BindView(R.id.dataView)
    View dataView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.item_title)
    TextView questionTitle;
    @BindView(R.id.item_body)
    MarkdownView questionBody;
    @BindView(R.id.item_vote)
    TextView questionVote;
    @BindView(R.id.item_answers)
    TextView questionAnswers;
    @BindView(R.id.item_views)
    TextView questionViews;
    @BindView(R.id.item_pic)
    ImageView questionOwnerPic;
    @BindView(R.id.item_owner)
    TextView questionOwnerName;
    @BindView(R.id.answerRecyclerView)
    RecyclerView mRecyclerView;
    AnswersRecyclerViewAdapter mAnswersRecyclerViewAdapter;
    AnswerPresenterImpl mAnswersPresenter;
    QuestionItem mQuestionItem;
    Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answers, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        mAnswersPresenter = new AnswerPresenterImpl(this);
        mAnswersPresenter.fetchAnswers(mQuestionItem.questionId);
    }

    public QuestionItem getQuestionItem() {
        return mQuestionItem;
    }

    public void setQuestionItem(QuestionItem questionItem) {
        mQuestionItem = questionItem;
    }

    @Override
    public void showErrorView() {
        dataView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.retryButton)
    public void retryButtonClick(View view) {
        showLoading();
        mAnswersPresenter.fetchAnswers(mQuestionItem.questionId);
    }

    @Override
    public void showLoading() {
        dataView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAnswers(List<AnswerItem> list) {
        questionTitle.setText(mQuestionItem.title);
        Log.d("TAG",mQuestionItem.body);
        questionBody.setMarkDownText(mQuestionItem.body);
        questionVote.setText(mQuestionItem.upVoteCount + "\nVotes");
        questionAnswers.setText(mQuestionItem.answerCount + "\nAnswers");
        questionViews.setText(mQuestionItem.viewCount + "\nViews");
        questionOwnerName.setText(mQuestionItem.owner.displayName + "\n" + mQuestionItem.owner.reputation);
        Picasso.with(getActivity()).load(mQuestionItem.owner.profileImage).resize(128, 128).into(questionOwnerPic);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAnswersRecyclerViewAdapter = new AnswersRecyclerViewAdapter(list, getActivity());
        mRecyclerView.setAdapter(mAnswersRecyclerViewAdapter);
        dataView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
