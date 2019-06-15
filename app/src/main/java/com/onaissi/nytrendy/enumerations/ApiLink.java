package com.onaissi.nytrendy.enumerations;

import com.onaissi.nytrendy.common.Constants;

public enum ApiLink {

    LatestArticles( "mostpopular/v2/viewed/7.json");

    private final String value;

    private ApiLink(String link){
        this.value = link;
    }

    public String getValue() {
        return Constants.BASE_URL + value;
    }
}
