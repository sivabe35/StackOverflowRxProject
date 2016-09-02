package com.gaurav.stackoverflowrxproject.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gaurav.stackoverflowrxproject.R;
import com.gaurav.stackoverflowrxproject.fragments.AnswersFragment;
import com.gaurav.stackoverflowrxproject.fragments.QuestionsFragment;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<QuestionItem> mQuestionModelList=null;
    Fragment currentFragment;
    int pagenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment=new QuestionsFragment();
        changeFragment(currentFragment);
    }

    public void changeFragment(Fragment fragment) {
        currentFragment=fragment;
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentHolder,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if(currentFragment instanceof AnswersFragment) {
            currentFragment=new QuestionsFragment();
            changeFragment(currentFragment);
        }else{
            super.onBackPressed();
        }
    }

    public List<QuestionItem> getQuestionModelList() {
        return mQuestionModelList;
    }

    public void setQuestionModelList(List<QuestionItem> questionModelList) {
        mQuestionModelList = questionModelList;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }
}
