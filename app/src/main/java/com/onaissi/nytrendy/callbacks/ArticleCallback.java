package com.onaissi.nytrendy.callbacks;

import com.onaissi.nytrendy.models.Article;
import java.util.ArrayList;

public interface ArticleCallback {

    void fetchLatestArticle(ArrayList<Article> articleArrayList);


}
