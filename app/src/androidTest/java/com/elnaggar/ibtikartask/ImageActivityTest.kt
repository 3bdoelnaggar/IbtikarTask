package com.elnaggar.ibtikartask


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnaggar.ibtikartask.features.image.ImageActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<ImageActivity> = ActivityScenarioRule(ImageActivity::class.java)

    // test  case when starting the image activity with null image extra that download button is hidden
    @Test
    fun startActivitWithEmptyExtra_ViewVisibilityGone() {
        // Type text and then press the button.
        onView(withId(R.id.downloadBt))
                .check(matches(not(isDisplayed())))


    }
}
