package com.gaurav.stackoverflowrxproject.models.questionmodels;

/**
 * Created by gaurav on 2/9/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {

    @SerializedName("items")
    @Expose
    public List<QuestionItem> items = new ArrayList<QuestionItem>();
}