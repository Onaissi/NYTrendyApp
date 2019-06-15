package com.onaissi.nytrendy.common;

import android.support.annotation.Nullable;

import com.android.volley.toolbox.ImageLoader;
import com.onaissi.nytrendy.custom_views.CircularImageView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class Utils {

    /***
     * Encode parammeters in a url as a GET style
     *
     * @param url url to be encoded
     * @param params parameter
     * @return full url encoded with provided paramters as GET
     */
    public static String encodeGetURL(String url, HashMap<String, String> params){
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        int i = 0;
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (i==0){
                i++;
                String toAdd = null;
                try {
                    toAdd = String.format("?%s=%s",key, URLEncoder.encode(value,"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(toAdd);
            }else{
                String toAdd = null;
                try {
                    toAdd = String.format("&%s=%s",key, URLEncoder.encode(value, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(toAdd);
            }
        }
        return sb.toString();
    }




    public static void loadImage(@Nullable String url, CircularImageView imageView) {
        ImageLoader mImageLoader = BaseApp.getInstance().getImageLoader();
        imageView.setImageUrl(null, mImageLoader);

        if (url != null && !url.isEmpty()){
            imageView.setImageUrl(url, mImageLoader);

        }

    }
}
