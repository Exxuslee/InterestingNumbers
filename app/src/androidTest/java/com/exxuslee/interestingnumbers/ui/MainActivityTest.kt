package com.exxuslee.interestingnumbers.ui

import com.exxuslee.interestingnumbers.R
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }


    @Test
    fun onKeyboardFragmentCardClicked_navigateToKeyboardFragmentAndBack() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.fabRandom)).check(matches(isDisplayed()))
        onView(withId(R.id.fabRandom)).perform(ViewActions.click())
        onView(withId(R.id.fabRandom)).perform(ViewActions.click())
        onView(withId(R.id.fabRandom)).perform(ViewActions.click())
        onView(withId(R.id.fabRandom)).perform(ViewActions.click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(ViewActions.click())
        pressBack()
        onView(withId(R.id.input)).perform(ViewActions.typeText("123"))
        onView(withId(R.id.fabNumber)).check(matches(isDisplayed()))
        onView(withId(R.id.fabNumber)).perform(ViewActions.click())
        onView(withId(R.id.recyclerView)).perform(ViewActions.click())
        pressBack()

        activityScenario.close()
    }
}