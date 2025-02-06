package ca.dal.cs.csci3130.a2;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {

    private static final int LAUNCH_TIMEOUT = 5000;
    final String launcherPackage = "ca.dal.cs.csci3130.a2";
    private UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(getInstrumentation());
        Context context = ApplicationProvider.getApplicationContext();
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackage);
        assert launchIntent != null;
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launchIntent);
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkIfLandingPageIsDisplayed() {
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        assertTrue(emailBox.exists());
        UiObject passwordBox = device.findObject(new UiSelector().text("Password"));
        assertTrue(passwordBox.exists());
        UiObject roleSpinner = device.findObject(new UiSelector().text("Select your role"));
        assertTrue(roleSpinner.exists());
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        assertTrue(registerButton.exists());
    }

    @Test
    public void checkIfMoved2WelcomePage() throws UiObjectNotFoundException {
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        emailBox.setText("abc.123@dal.ca");

        UiObject passwordBox = device.findObject(new UiSelector().text("Password"));
        passwordBox.setText("pass123!@");

        UiObject roleSpinner = device.findObject(new UiSelector().text("Select your role"));
        roleSpinner.click();
        UiObject buyerRole = device.findObject(new UiSelector().resourceId("android:id/text1").text("Buyer"));
        buyerRole.click();

        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        registerButton.clickAndWaitForNewWindow();

        // 🔹 Add delay to allow UI transition
        device.wait(Until.hasObject(By.textContains("Hi there! Your role is:")), 5000);

        UiObject welcomeLabel = device.findObject(new UiSelector().textContains("Hi there! Your role is:"));
        assertTrue("Welcome message not found!", welcomeLabel.exists());
    }


    @Test
    public void checkIfCredentialsAreRetrieved() throws UiObjectNotFoundException {
        // Ensure we are on the MainActivity (Registration Page)
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        emailBox.setText("test.user@dal.ca");

        UiObject passwordBox = device.findObject(new UiSelector().text("Password"));
        passwordBox.setText("Pass123!@");

        UiObject roleSpinner = device.findObject(new UiSelector().text("Select your role"));
        roleSpinner.click();
        UiObject buyerRole = device.findObject(new UiSelector().resourceId("android:id/text1").text("Buyer"));
        buyerRole.click();

        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        registerButton.clickAndWaitForNewWindow();

        // Wait for WelcomeActivity to load
        device.wait(Until.hasObject(By.textContains("Hi there! Your role is:")), 5000);

        // Click the "Retrieve Credentials" button
        UiObject retrieveButton = device.findObject(new UiSelector().text("RETRIEVE CREDENTIALS"));
        retrieveButton.click();

        // Wait for Snackbar message
        device.wait(Until.hasObject(By.textStartsWith("Email: test.user@dal.ca")), 5000);

        // heck if retrieved credentials are correctly displayed
        UiObject emailLabel = device.findObject(new UiSelector().textStartsWith("Email: test.user@dal.ca"));
        assertTrue("Retrieved credentials not found!", emailLabel.exists());
    }

}