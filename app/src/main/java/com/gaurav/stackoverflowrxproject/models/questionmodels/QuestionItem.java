package com.gaurav.stackoverflowrxproject.models.questionmodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionItem {

    @SerializedName("owner")
    @Expose
    public QuestionOwner owner;
    @SerializedName("view_count")
    @Expose
    public Integer viewCount;
    @SerializedName("up_vote_count")
    @Expose
    public Integer upVoteCount;
    @SerializedName("answer_count")
    @Expose
    public Integer answerCount;
    @SerializedName("body_markdown")
    @Expose
    public String body;
    @SerializedName("question_id")
    @Expose
    public Integer questionId;
    @SerializedName("title")
    @Expose
    public String title;

}