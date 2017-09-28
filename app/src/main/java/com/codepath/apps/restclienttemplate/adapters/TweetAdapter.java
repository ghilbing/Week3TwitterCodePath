package com.codepath.apps.restclienttemplate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by gretel on 9/25/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{



    private List<Tweet> mTweets;
    Context mContext;

    //pass in the Tweets array in the constructor
    public TweetAdapter(Context context, List<Tweet> tweets){
        mContext = context;
        mTweets = tweets;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View tweetView = inflater.inflate(R.layout.item_tweet, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Get the data according to position
        Tweet tweet = mTweets.get(position);

        //populate the views according to this data
        holder.tvUserName.setText("@" + tweet.getUser().getScreenName().toString());
        holder.tvBody.setText(tweet.getBody());
        holder.tvFullName.setText(tweet.getUser().getName());
        holder.tvRetweetCount.setText(String.valueOf(tweet.getRetweetCount()));
        holder.tvTweetAge.setText(tweet.getCreatedAt());

        Glide.with(mContext).load(tweet.getUser().getProfileImage()).into(holder.ivProfileImage);

    }

    public void clear() {
        int size = this.mTweets.size();
        this.mTweets.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void add(int position, Tweet tweet){
        mTweets.add(position, tweet);
        notifyItemInserted(position);
    }

    public void addAll(ArrayList<Tweet> tweets){
        mTweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public Tweet getItem(int position){
        return mTweets.get(position);
    }






    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //for each row, inflate the layout and cache the references into ViewHolder



    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.ivProfileImage)
        ImageView ivProfileImage;
        @Bind(R.id.tvUserName)
        TextView tvUserName;
        @Bind(R.id.tvBody)
        TextView tvBody;
        @Bind(R.id.tvTweetFullName)
        TextView tvFullName;
        @Bind(R.id.tvTweetAge)
        TextView tvTweetAge;
        @Bind(R.id.tvRetweetCount)
        TextView tvRetweetCount;

        public ViewHolder (View itemView){
            super(itemView);

            ButterKnife.bind(this, itemView);
        }



    }
}
