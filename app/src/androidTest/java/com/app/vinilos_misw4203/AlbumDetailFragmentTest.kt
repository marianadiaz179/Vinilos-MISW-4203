package com.app.vinilos_misw4203

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDetailFragmentTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun navigateToAlbumsFragment() {
        // Usa el botón correcto de navegación (ajusta si tu ID es diferente)
        onView(allOf(withId(R.id.albumContainer), isDisplayed())).perform(click())

        Thread.sleep(3000)

        onView(allOf(withText("Álbumes"), isDescendantOfA(withId(R.id.albumFragment))))
            .check(matches(isDisplayed()))

        onView(withId(R.id.albumsRv)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun clickFirstAlbum() {
        navigateToAlbumsFragment()
        // Asegura que se puede hacer clic en el primer álbum
        onView(allOf(withId(R.id.albumsRv), isDisplayed())).perform(click())
    }

    @Test
    fun verifyAlbumDetailViewsVisible() {
        clickFirstAlbum()
        Thread.sleep(3000)
        onView(withId(R.id.albumImage)).check(matches(isDisplayed()))
        onView(withId(R.id.albumName)).check(matches(isDisplayed()))
        onView(withId(R.id.albumReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.albumGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.albumDescription)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyCommentsSectionVisible() {
        verifyAlbumDetailViewsVisible()
        Thread.sleep(3000)
        onView(allOf(withId(R.id.commentsTitle), withText("Comentarios"))).check(matches(isDisplayed()))
        onView(withId(R.id.commentsRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun verifyProgressBarHidden() {
        Thread.sleep(3000)
        verifyAlbumDetailViewsVisible()
        onView(withId(R.id.progressBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}