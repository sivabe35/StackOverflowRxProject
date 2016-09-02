package com.gaurav.stackoverflowrxproject.models.answermodels;

/**
 * Created by gaurav on 2/9/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnswerModel {

    @SerializedName("items")
    @Expose
    public List<AnswerItem> items = new ArrayList<AnswerItem>();
}