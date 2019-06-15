package com.onaissi.nytrendy.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onaissi.nytrendy.R;
import com.onaissi.nytrendy.callbacks.ArticleListCallback;
import com.onaissi.nytrendy.common.Utils;
import com.onaissi.nytrendy.custom_views.CircularImageView;
import com.onaissi.nytrendy.models.Article;

import java.util.ArrayList;

public class ArticleListAdapter extends Adapter<ArticleListAdapter.ArticleHolder> {

    private ArrayList<Article> mValues;
    private final ArticleListCallback mArticleListCallback;

    public ArticleListAdapter(ArrayList<Article> items, ArticleListCallback callback) {
        mValues = items;
        mArticleListCallback = callback;

    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_article_list, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, int position) {
        Article article = mValues.get(position);
        holder.itemView.setTag(article);
        holder.onBind(article);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public void setItems(ArrayList<Article> articleArrayList) {
        this.mValues = articleArrayList;
        notifyDataSetChanged();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {
        CircularImageView mainImageView;
        TextView titleTV;
        TextView byLineTV;
        TextView datePublishedTV;
        TextView sourceTV;

        void onBind(Article article) {
            Utils.loadImage(article.getImageUrl(),mainImageView);
            titleTV.setText(article.getTitle());
            byLineTV.setText(article.getByLine());
            datePublishedTV.setText(article.getPublishDate());
            sourceTV.setText(article.getSource());
        }


        private ArticleHolder(@NonNull View itemView) {
            super(itemView);
            mainImageView = itemView.findViewById(R.id.article_list_content_image);
            titleTV = itemView.findViewById(R.id.article_list_content_title_tv);
            byLineTV = itemView.findViewById(R.id.article_list_content_byline_tv);
            datePublishedTV = itemView.findViewById(R.id.article_list_content_published_date_tv);
            sourceTV = itemView.findViewById(R.id.article_list_content_source_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mArticleListCallback.articleClick(getLayoutPosition(), view);
                    notifyDataSetChanged();
                }
            });
        }
    }


}
