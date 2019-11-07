package com.testapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.testapp.data.network.utils.NetworkState;
import com.testapp.R;
import com.testapp.data.database.model.NewsAPIItem;

public class NewsListAdapter extends PagedListAdapter<NewsAPIItem, RecyclerView.ViewHolder> {
    private static final int TYPE_NETWORK_STATUS = 0;
    private static final int TYPE_ITEM = 1;
    private boolean isFooter = false;
    private NetworkState oldnetState, networkState = null;

    public interface OnItemClickListener
    {
        void onItemClick(View view);
    }
    private final OnItemClickListener listener;

    public NewsListAdapter(@NonNull DiffUtil.ItemCallback<NewsAPIItem> diffCallback, OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFooter && isFooterPosition(position))
        {
            return TYPE_NETWORK_STATUS;
        }
        else
        {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount()
    {
        int itemCount = 0;
        if (getCurrentList() != null) itemCount = getCurrentList().size();
        if (isFooter) itemCount++;
        return itemCount;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NETWORK_STATUS)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.network_state_item, parent, false);
            return new NetworkItemViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            return new NewsItemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsItemViewHolder)
        {
            ((NewsItemViewHolder)holder).bind(getItem(position));
            ((NewsItemViewHolder)holder).itemView.setOnClickListener(listener::onItemClick);

        }

        if (holder instanceof NetworkItemViewHolder)
        {
            ((NetworkItemViewHolder)holder).bind(networkState);
            ((NetworkItemViewHolder)holder).btnNetworkStateRetry.setOnClickListener(listener::onItemClick);
        }
    }
    private boolean isFooterPosition(int position)
    {
        return position == getItemCount() - 1;
    }

    public void UpdateFooter(NetworkState netState)
    {
        networkState = netState;
        if (netState.getStatus() != NetworkState.Status.SUCCESS && !isFooter)
        {
            isFooter = true;
            int pos = getItemCount() - 1;
            notifyItemInserted(pos);
        }

        if (oldnetState != netState && isFooter)
        {
            isFooter = true;
            int pos = getItemCount() - 1;
            notifyItemChanged(pos);
        }

        if (netState.getStatus() == NetworkState.Status.SUCCESS && isFooter)
        {
            isFooter = false;
            int pos = getItemCount();
            notifyItemRemoved(pos);
        }

        oldnetState = netState;
    }

}
