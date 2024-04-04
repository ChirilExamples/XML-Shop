package com.example.afinal

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.afinal.presentation.adapter.RecyclerAdapter
import com.example.afinal.presentation.fragment.FirstFragment
import org.junit.Before
import org.junit.Test

class FirstFragmentTest {
    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<FirstFragment>(Bundle(), R.style.Theme_Final) {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun checkIfRecycleViewIsVisible() {
        Espresso.onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun recyclerViewItemClickShouldGoToDetails() {
        Espresso.onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapter.MyViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
        assert(navController.currentDestination?.id == R.id.SecondFragment)
    }
}
