package com.onaissi.nytrendy;

import android.support.v7.app.AppCompatViewInflater;

import com.onaissi.nytrendy.common.BaseApp;
import com.onaissi.nytrendy.common.Constants;
import com.onaissi.nytrendy.common.Utils;
import com.onaissi.nytrendy.enumerations.ApiLink;

import org.junit.Test;

import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void urlEncode_isCorrect(){
        HashMap<String, String> params = new HashMap<>();
        params.put("a", "12");
        params.put("b", "My name is Ahmad");
        URL url = null;
        try {
            String urlString =  Utils.encodeGetURL("http://expample.com", params);
            url = new URL(urlString);
        }catch (Exception e){
            fail();
        }

        assertNotNull(url);
    }

    @Test
    public void baseAppSingleton(){
        BaseApp baseApp = BaseApp.getInstance();
        BaseApp baseApp1 = BaseApp.getInstance();
        assertSame(baseApp,baseApp1);
    }

    @Test
    public void ApiLinkTest(){
        String a = ApiLink.LatestArticles.getValue();
        assertEquals(a, Constants.BASE_URL + "mostpopular/v2/viewed/7.json");
    }





}