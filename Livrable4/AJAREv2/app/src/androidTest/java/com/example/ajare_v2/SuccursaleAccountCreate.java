package com.example.ajare_v2;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SuccursaleAccountCreate {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void succursaleAccountCreate() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.connexion), withText("CONNEXION"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                9),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.emailll), withText("EMAIL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.emailAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("nikee@novigrad.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.passworddd),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.loginnn), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.loginnn), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.loginnn), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.emailAddress), withText("nikee@novigrad.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("nike@novigrad.com"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.emailAddress), withText("nike@novigrad.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.loginnn), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.succursaleConnect), withText("Connect Dep"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer),
                                        1),
                                13),
                        isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.emailAddress_succursale),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("ottnovigrad@novigrad.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.passworddd_succursale),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.loginnn_succursale), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.loginnn_succursale), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.passworddd_succursale), withText("1234567"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("1234567"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.passworddd_succursale), withText("1234567"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.loginnn_succursale), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                10),
                        isDisplayed()));
        appCompatButton10.perform(click());

        pressBack();

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.succursaleSignIn), withText("Create Dep "),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer),
                                        1),
                                14),
                        isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.emailedit),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("ottnovigrad@novigrad.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.passwordedit),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("1234567"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.editTextPhone),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("111-111-1111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.serviceEdit),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("ID"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.adressedit),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("K3V7X4"), closeSoftKeyboard());

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.create), withText("Create"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton12.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
