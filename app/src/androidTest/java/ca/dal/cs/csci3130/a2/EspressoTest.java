package ca.dal.cs.csci3130.a2;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.dal.cs.csci3130.a2.ui.MainActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    public ActivityScenario<MainActivity> scenario;

    @Before
    public void setup() {
        scenario = ActivityScenario.launch(MainActivity.class);
        scenario.onActivity(activity -> {
            activity.loadRoleSpinner();
            activity.setupRegistrationButton();
        });
    }

    @Test
    public void checkIfEmailIsEmpty() {
        onView(withId(R.id.emailBox)).perform(typeText(""));
        onView(withId(R.id.passwordBox)).perform(typeText("pass123!@"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Seller"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_EMAIL_ADDRESS)));
    }

    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.emailBox)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("pass456!@"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Buyer"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkIfEmailIsNotValid() {
        onView(withId(R.id.emailBox)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("pass456!@"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Seller"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    @Test
    public void checkIfEmailIsNotFromDAL() {
        onView(withId(R.id.emailBox)).perform(typeText("abc123@usask.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("pass123!#"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Buyer"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_DAL_EMAIL)));
    }

    @Test
    public void checkIfPasswordIsValid() {
        onView(withId(R.id.emailBox)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("pass123!@"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Seller"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkIfPasswordIsNotValid() {
        onView(withId(R.id.emailBox)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("pass13!&"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Buyer"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_PASSWORD)));
    }

    @Test
    public void checkIfRoleIsValid() {
        onView(withId(R.id.emailBox)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.passwordBox)).perform(typeText("paws183!@"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Buyer"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText("")));
    }

    @Test
    public void checkIfRoleIsNotValid() {
        //inappropriate test, redesign the test!
        assertEquals(2+2, 5);
    }
}