package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.R;
import com.codepath.apps.restclienttemplate.TwitterClient;
import com.codepath.apps.restclienttemplate.adapters.TweetAdapter;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.apps.restclienttemplate.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gretel on 9/25/17.
 */

public abstract class TweetsFragment extends Fragment {

    @Bind(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @Bind(R.id.rvTweet)
    RecyclerView mRecycler;

    TweetAdapter mAdapter;



    public TweetsFragment() {

    }

    public abstract void populateTimeline(String maxId);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (isNetworkAvailable()){
            populateTimeline(null);

        }else {
            Toast.makeText(getActivity().getApplicationContext(), "No Internet connection", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_tweets, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new TweetAdapter(getActivity(), new ArrayList<Tweet>());
        mRecycler.setAdapter(mAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity()));
       /* mRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
               // Log.i( + (mAdapter.getItemCount()));
                if(mAdapter.getItemCount() == 0){
                    populateTimeline(null);
                } else if (mAdapter.getItemCount() >= TwitterClient.T_X_PAGE){
                    Tweet oldest = mAdapter.getItem(mAdapter.getItemCount()-1);
                    populateTimeline(oldest.getUid());
                }
            }
        });*/

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clear();
                mAdapter.notifyDataSetChanged();
                populateTimeline(null);
            }
        });

        return view;


    }

    protected boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public void add(Tweet tweet){
        mAdapter.add(0, tweet);
        mAdapter.notifyDataSetChanged();

    }

    public void addAll(ArrayList<Tweet> tweets){
        mAdapter.addAll(tweets);
        mAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }


}
