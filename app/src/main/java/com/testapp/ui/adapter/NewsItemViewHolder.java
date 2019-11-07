package com.testapp.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.testapp.R;
import com.testapp.data.database.model.NewsAPIItem;
import com.testapp.ui.utils.DateConverter;
import com.testapp.ui.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivNewsItemImage) ImageView ivNewsItemImage;
    @BindView(R.id.tvNewsItemTitle) TextView tvNewsItemTitle;
    @BindView(R.id.tvNewsItemDescriprion) TextView tvNewsItemDescriprion;
    @BindView(R.id.tvNewsItemDate) TextView tvNewsItemDate;

    public NewsItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(NewsAPIItem news)
    {
        tvNewsItemTitle.setText(news.getTitle());
        tvNewsItemDescriprion.setText(news.getDescription());
        tvNewsItemDate.setText(DateConverter.convert(news.getPublishedAt()));
        itemView.setTag(news.getUrl());

        GlideApp
                .with(itemView.getContext())
                .load(news.getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.progress_placeholder)
                .into(ivNewsItemImage);


    }
}
