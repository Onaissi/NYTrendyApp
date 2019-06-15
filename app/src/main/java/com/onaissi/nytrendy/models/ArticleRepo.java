package com.onaissi.nytrendy.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.onaissi.nytrendy.callbacks.ArticleCallback;
import com.onaissi.nytrendy.callbacks.GeneralCallback;
import com.onaissi.nytrendy.common.BaseApp;
import com.onaissi.nytrendy.common.Constants;
import com.onaissi.nytrendy.common.Utils;
import com.onaissi.nytrendy.enumerations.ApiLink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class ArticleRepo extends Reposotory {

    private static final String REQUEST_TAG  = "articles_repo_requests";
    private @NonNull ArticleCallback articleCallback;

    public ArticleRepo(Context context, ArticleCallback articleCallback) {
        super(context);
        this.articleCallback = articleCallback;

    }


    public void requestLatestArticles() {

        final String URL = ApiLink.LatestArticles.getValue();
        showProgressDialog();

        HashMap<String, String> params = new HashMap<>();
        params.put("api-key", Constants.API_KEY);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Utils.encodeGetURL(URL, params), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(REQUEST_TAG, "success response : " + response.toString());
                hideProgressDialog();
                try{
                    ArrayList<Article> articles = parseArticleListJson(response);
                    articleCallback.fetchLatestArticle(articles);
                }catch (JSONException | MalformedURLException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(REQUEST_TAG, "failed response : " + error.toString());
                processFailBlock(error, new GeneralCallback() {
                    @Override
                    public void handler() {
                        requestLatestArticles();
                    }
                });

            }
        });

        BaseApp.getInstance().addToRequestQueue(jsonObjectRequest, REQUEST_TAG);
    }


    private Article parseArticleJson(JSONObject jsonObject) throws JSONException, MalformedURLException {
        URL url = new URL(jsonObject.getString("url"));

        @Nullable String imageUrl = null;
        if (jsonObject.has("media")){
            JSONArray mediaArray = jsonObject.getJSONArray("media");
            for (int i =0; i<mediaArray.length(); i++){
                JSONObject mediaJson = (JSONObject) mediaArray.get(i);
                if (mediaJson.has("type") && mediaJson.getString("type").equals("image")){
                    JSONArray metaArray = mediaJson.getJSONArray("media-metadata");
                    for (int j=0; j<metaArray.length(); j++){
                        JSONObject metaJson = (JSONObject) metaArray.get(i);
                        if (metaJson.has("format") && metaJson.getString("format").equals("Standard Thumbnail")){
                            imageUrl = metaJson.getString("url");

                        }
                    }
                }
            }
        }
        Log.d("Adapter ", "parseArticleJson: image url " + imageUrl);

        return new Article(jsonObject.getString("title"),
                jsonObject.getString("abstract"),
                jsonObject.getString("section"),
                jsonObject.getString("byline"),
                url,
                jsonObject.getString("published_date"),
                jsonObject.getString("source"),
                imageUrl);
    }


    private ArrayList<Article> parseArticleListJson(JSONObject jsonObject) throws JSONException, MalformedURLException{
        ArrayList<Article> articleList = new ArrayList<>();
        JSONArray results = jsonObject.getJSONArray("results");
        for (int i = 0; i < results.length(); i++){
                JSONObject json = (JSONObject) results.get(i);
                Article anArticle = parseArticleJson(json);
                articleList.add(anArticle);
            }

        return articleList;
    }
}
