package ca.dal.cs.csci3130.a2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.dal.cs.csci3130.a2.validator.CredentialValidator;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    CredentialValidator validator;

    @Before
    public void setup() {
        validator = new CredentialValidator();
    }

    @Test
    public void checkIfEmailIsEmpty() {
        Assert.assertTrue(validator.isEmptyEmailAddress(""));
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(validator.isValidEmailAddress("abc123@dal.ca"));
    }

    @Test
    public void checkIfEmailIsNotValid() {
        assertFalse(validator.isValidEmailAddress("abc.123dal.ca"));
    }

    @Test
    public void checkIfEmailIsNotFromDAL() {
        assertTrue(validator.isDALEmailAddress("abc.123@dal.ca"));
        assertFalse(validator.isDALEmailAddress("abc.123@usask.ca"));
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertTrue(validator.isValidPassword("pass122!@"));
        assertTrue(validator.isValidPassword("pass123!#"));
        assertTrue(validator.isValidPassword("hello12@#"));
    }

    @Test
    public void checkIfPasswordIsNotValid() {
        assertFalse(validator.isValidPassword("pa!1223"));
        assertFalse(validator.isValidPassword("paws12#"));
        assertFalse(validator.isValidPassword("passwor@"));
        assertFalse(validator.isValidPassword("1234567!@"));
    }

    @Test
    public void checkIfRoleIsValid() {
        assertTrue(validator.isValidRole("Buyer"));
        assertTrue(validator.isValidRole("Seller"));
    }

    @Test
    public void checkIfRoleIsNotValid() {
        assertFalse(validator.isValidRole("Select your role"));
        assertFalse(validator.isValidRole("Admin"));
        assertFalse(validator.isValidRole(""));
    }


}