package com.gaurav.stackoverflowrxproject.models.answermodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerItem {

    @SerializedName("owner")
    @Expose
    public AnswerOwner owner;
    @SerializedName("is_accepted")
    @Expose
    public Boolean isAccepted;
    @SerializedName("body_markdown")
    @Expose
    public String body;
}