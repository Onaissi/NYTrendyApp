package com.onaissi.nytrendy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onaissi.nytrendy.R;
import com.onaissi.nytrendy.adapters.ArticleListAdapter;
import com.onaissi.nytrendy.callbacks.ArticleCallback;
import com.onaissi.nytrendy.callbacks.ArticleListCallback;
import com.onaissi.nytrendy.models.Article;
import com.onaissi.nytrendy.models.ArticleRepo;

import java.util.ArrayList;

public class ArticleListFragment extends Fragment implements ArticleListCallback, ArticleCallback {

    public static final String ARG_ITEM_ID = "item_id";

    private static final String TAG = ArticleListFragment.class.getSimpleName();

    private @NonNull RecyclerView mRecyclerView;
    private @NonNull ArrayList<Article> mArticleList = new ArrayList<>();
    private @Nullable ArticleListAdapter mAdapter;

    public ArticleListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            Activity activity = this.getActivity();
//
//        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_article_list, container, false);
        bindViews(rootView);
        baseInit(savedInstanceState);
        getData();

        return rootView;
    }


    private void baseInit(Bundle savedInstanceState){
        setupRecyclerView((RecyclerView) mRecyclerView);

    }

    private void bindViews(View v){
        mRecyclerView = v.findViewById(R.id.mostpopular_list);

    }

    private void getData(){
        ArticleRepo articleRepo = new ArticleRepo(getContext(), this);
        articleRepo.requestLatestArticles();
    }

    @Override
    public void fetchLatestArticle(ArrayList<Article> articleArrayList) {

        this.mArticleList = articleArrayList;
        if (mAdapter != null){
            mAdapter.setItems(this.mArticleList);
        }
    }



    @Override
    public void articleClick(int position, View view) {
        Article article = mArticleList.get(position);
        Log.d(TAG, "articleClick: open url " + article.getUrl().toString());
        Fragment fragment = new DetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailsFragment.EXTRA_ARTICLE,article);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment,DetailsFragment.ARG_ITEM_ID).addToBackStack(DetailsFragment.ARG_ITEM_ID).commit();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        LinearLayoutManager layoutManagerCompany = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mAdapter = new ArticleListAdapter(new ArrayList<Article>(),this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManagerCompany);
    }
}
