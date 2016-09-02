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
import com.gaurav.stackoverflowrxproject.models.answermodels.AnswerItem;
import com.mukesh.MarkdownView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gaurav on 2/9/16.
 */
public class AnswersRecyclerViewAdapter extends RecyclerView.Adapter<AnswersRecyclerViewAdapter.MyViewHolder> {
    List<AnswerItem> mItems;
    Context mContext;

    public AnswersRecyclerViewAdapter(List<AnswerItem> items, Context context) {
        mItems = items;
        mContext = context;
    }

    @Override
    public AnswersRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_answerview, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AnswersRecyclerViewAdapter.MyViewHolder holder, int position) {
        if (!mItems.get(position).isAccepted) {
            holder.accepted.setVisibility(View.GONE);
        }
        holder.body.setMarkDownText(mItems.get(position).body);
        holder.ownernamereputation.setText(mItems.get(position).owner.displayName + "\n" + mItems.get(position).owner.reputation);
        Picasso.with(mContext)
                .load(mItems.get(position).owner.profileImage)
                .resize(128, 128)
                .into(holder.ownerimage);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_isacceptedanswer)
        ImageView accepted;
        @BindView(R.id.item_body)
        MarkdownView body;
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
