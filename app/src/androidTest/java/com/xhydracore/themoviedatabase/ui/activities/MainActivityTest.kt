package com.xhydracore.themoviedatabase.ui.activities

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewVisibility() {
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDetailViewDataMovie() {
        val getMovieList = MoviesRepository.getMoviesData()
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(withText(getMovieList[0].movieTitle)))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText(getMovieList[0].movieReleaseDate)))
        Espresso.onView(withId(R.id.tv_tagline))
            .check(ViewAssertions.matches(withText(getMovieList[0].movieGenres.joinToString(" | "))))
        Espresso.onView(withId(R.id.rating_bar_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDetailViewDataTvShow() {
        val getTvShowList = TvShowRepository.getTvShowData()
        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(withText(getTvShowList[0].tvShowTitle)))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText(getTvShowList[0].tvShowReleaseDate)))
        Espresso.onView(withId(R.id.tv_tagline))
            .check(ViewAssertions.matches(withText(getTvShowList[0].tvShowGenres.joinToString(" | "))))
        Espresso.onView(withId(R.id.rating_bar_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDetailBackButtonMovie() {
        Espresso.onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.btn_back_detail)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testDetailBackButtonTvShow() {
        Espresso.onView(withId(R.id.navigation_tvshow)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.btn_back_detail)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}