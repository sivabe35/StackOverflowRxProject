package com.gaurav.stackoverflowrxproject.network;

import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerModel;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gaurav on 2/9/16.
 */
public interface StackOverflowService {
    @GET("questions?pagesize=5&order=desc&sort=activity&site=stackoverflow&filter=!.FjtmoGIogpSTdXxMSmzZb*32ax4i")
    Observable<QuestionModel> getQuestions(@Query("page") int pageno, @Query("tagged") String tag);

    @GET("questions/{id}/answers?page=1&pagesize=5&order=desc&sort=activity&site=stackoverflow&filter=!)Q2AgQTb-WmAUBSAtye3vNCA")
    Observable<AnswerModel> getAnswers(@Path("id")long question_id);
}
