package com.gaurav.stackoverflowrxproject.models.answermodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by gaurav on 2/9/16.
 */

public class AnswerOwner {

    @SerializedName("reputation")
    @Expose
    public Integer reputation;
    @SerializedName("profile_image")
    @Expose
    public String profileImage;
    @SerializedName("display_name")
    @Expose
    public String displayName;

}
