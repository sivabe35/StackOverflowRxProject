package com.gaurav.stackoverflowrxproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaurav.stackoverflowrxproject.R;
import com.gaurav.stackoverflowrxproject.models.questionmodels.QuestionItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 2/9/16.
 */
public class QuestionsRecyclerViewAdapter extends RecyclerView.Adapter<QuestionsRecyclerViewAdapter.MyViewHolder> {
    List<QuestionItem> mItems;
    Context mContext;

    public QuestionsRecyclerViewAdapter(List<QuestionItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @Override
    public QuestionsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_questiontitleview, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(QuestionsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mItems.get(position).title);
        holder.votes.setText(mItems.get(position).upVoteCount + "\nVotes");
        holder.answers.setText(mItems.get(position).answerCount + "\nAnswers");
        holder.views.setText(mItems.get(position).viewCount + "\nViews");
        holder.ownernamereputation.setText(mItems.get(position).owner.displayName + "\n" +
                mItems.get(position).owner.reputation);
        Picasso.with(mContext)
                .load(mItems.get(position).owner.profileImage)
                .resize(128,128)
                .into(holder.ownerimage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView title;
        @BindView(R.id.item_vote)
        TextView votes;
        @BindView(R.id.item_answers)
        TextView answers;
        @BindView(R.id.item_views)
        TextView views;
        @BindView(R.id.item_owner)
        TextView ownernamereputation;
        @BindView(R.id.item_pic)
        ImageView ownerimage;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
