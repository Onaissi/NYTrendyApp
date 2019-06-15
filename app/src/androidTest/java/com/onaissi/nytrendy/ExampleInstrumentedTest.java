package com.onaissi.nytrendy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            true);


    @Before
    public void registerIdlingResource() {
        try {
        Thread.sleep(3500);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }




    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.onaissi.nytrendy", appContext.getPackageName());
    }

    @Test
    public void testRecycleViewClick(){

        onView(withId(R.id.mostpopular_list))
                .inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.fragment_article_details_title_tv)).check(matches(not(withText(""))));
    }

    @Test
    public void testRecycleViewScroll(){


        RecyclerView recyclerView = activityRule.getActivity().getWindow().findViewById(R.id.mostpopular_list);
        int countItems = recyclerView.getAdapter().getItemCount();

        onView(withId(R.id.mostpopular_list))
                .inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(RecyclerViewActions.actionOnItemAtPosition(countItems - 1,click()));

    }


    @Test
    public void testViewRecycleViewItem(){
        onView(withId(R.id.mostpopular_list))
                .inRoot(RootMatchers.withDecorView(Matchers.is(activityRule.getActivity().getWindow().getDecorView())))
                .perform(scrollToPosition(1))
                .check(matches(withViewAtPosition(1, Matchers.anyOf(
                        ViewMatchers.withId(R.id.article_list_content_title_tv), isDisplayed()))));
    }





    public Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }


}
