package com.onaissi.nytrendy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onaissi.nytrendy.R;
import com.onaissi.nytrendy.models.Article;
import com.onaissi.nytrendy.models.ArticleRepo;

public class DetailsFragment extends Fragment {

    public static final String ARG_ITEM_ID = "details_fragment";
    public static final String EXTRA_ARTICLE = "extra_article_object";
    private TextView titleTv;
    private TextView extraDetailsTv;
    private TextView abstractTv;

    private @NonNull Article mArticle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_article_details, container, false);
        bindViews(rootView);
        baseInit(savedInstanceState);


        return rootView;
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args != null && args.containsKey(EXTRA_ARTICLE)){
            mArticle = args.getParcelable(EXTRA_ARTICLE);
        }
    }

    private void baseInit(Bundle savedInstanceState){
        updateViews();
    }

    private void bindViews(View v){
        titleTv = v.findViewById(R.id.fragment_article_details_title_tv);
        extraDetailsTv = v.findViewById(R.id.fragment_article_details_extra_details_tv);
        abstractTv = v.findViewById(R.id.fragment_article_details_abstract_tv);

    }

    private void updateViews(){
        if (mArticle == null){
            return;
        }

        titleTv.setText(mArticle.getTitle());
        extraDetailsTv.setText(mArticle.description());
        abstractTv.setText(mArticle.getAbstractDescription());

    }


}
