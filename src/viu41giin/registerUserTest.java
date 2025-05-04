package viu41giin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import viu41giin.registerUser;

public class registerUserTest {

    static registerUser[] usuVal = new registerUser[10];
    static registerUser usuInval;

    @BeforeClass
    public static void beforeClassCreateValidUsers() {
        System.out.println("beforeClassCreateValidUser()");
        // creo usuarios validos
        for (int i = 0; i < 10; i++) {
            registerUser regtmp = new registerUser("validUser00"+i,"valid"+i+"@example.com","ValidPass00"+i,"ValidPass00"+i);
            usuVal[i]= regtmp;
            assertEquals("Comprobamos el numero de usuario valido creado ", regtmp , usuVal[i]);
        }
        usuInval= new registerUser("invalid-user!","@example.com","ValidPass00","ValidPass00");
        assertEquals("Comprobamos el numero de usuarios validos", 10, usuVal.length);
    }

    // Ahora empiezo a validar 
    @Test
    public void testValidUserRegistration() {
        assertTrue(usuVal[0].validate());
    }

 /*    // Username validation tests
    @Test
    void testUsername_TooShort() {
        registerUser user = new registerUser(
            "abc", // too short
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidUsername());
    }

    @Test
    void testUsername_TooLong() {
        registerUser user = new registerUser(
            "thisusernameiswaytoolongforvalidation", // too long
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidUsername());
    }

    @Test
    void testUsername_InvalidCharacters() {
        registerUser user = new registerUser(
            "invalid-user!", // contains invalid characters
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidUsername());
    }

    @Test
    void testUsername_Null() {
        registerUser user = new registerUser(
            null,
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidUsername());
    }

    @Test
    void testUsername_Empty() {
        registerUser user = new registerUser(
            "",
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidUsername());
    }

    // Email validation tests
    @Test
    void testEmail_Valid() {
        registerUser user = createValidUser();
        assertTrue(user.isValidEmail());
    }

    @Test
    void testEmail_InvalidFormat() {
        registerUser user = new registerUser(
            "validUser123",
            "invalid.email",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidEmail());
    }

    @Test
    void testEmail_MissingAtSymbol() {
        registerUser user = new registerUser(
            "validUser123",
            "invalid.example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidEmail());
    }

    @Test
    void testEmail_Null() {
        registerUser user = new registerUser(
            "validUser123",
            null,
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidEmail());
    }

    @Test
    void testEmail_Empty() {
        registerUser user = new registerUser(
            "validUser123",
            "",
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.isValidEmail());
    }

    // Password validation tests
    @Test
    void testPassword_TooShort() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "Short1", // too short
            "Short1"
        );
        assertFalse(user.isValidPassword());
    }

    @Test
    void testPassword_MissingDigit() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "NoDigitHere",
            "NoDigitHere"
        );
        assertFalse(user.isValidPassword());
    }

    @Test
    void testPassword_MissingLowercase() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "ALLUPPER123",
            "ALLUPPER123"
        );
        assertFalse(user.isValidPassword());
    }

    @Test
    void testPassword_MissingUppercase() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "alllower123",
            "alllower123"
        );
        assertFalse(user.isValidPassword());
    }

    @Test
    void testPassword_Null() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            null,
            null
        );
        assertFalse(user.isValidPassword());
    }

    @Test
    void testPassword_Empty() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "",
            ""
        );
        assertFalse(user.isValidPassword());
    }

    // Password matching tests
    @Test
    void testPasswordsMatch_Valid() {
        registerUser user = createValidUser();
        assertTrue(user.passwordsMatch());
    }

    @Test
    void testPasswordsMatch_Invalid() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "ValidPass123",
            "DifferentPass123"
        );
        assertFalse(user.passwordsMatch());
    }

    @Test
    void testPasswordsMatch_OneNull() {
        registerUser user = new registerUser(
            "validUser123",
            "valid@example.com",
            "ValidPass123",
            null
        );
        assertFalse(user.passwordsMatch());
    }

    // Comprehensive validation tests
    @Test
    void testValidate_AllValid() {
        registerUser user = createValidUser();
        assertTrue(user.validate());
    }

    @Test
    void testValidate_OneInvalidField() {
        registerUser user = new registerUser(
            "validUser123",
            "invalid.email", // invalid email
            "ValidPass123",
            "ValidPass123"
        );
        assertFalse(user.validate());
    }

    @Test
    void testValidate_MultipleInvalidFields() {
        registerUser user = new registerUser(
            "inv", // invalid username
            "invalid.email", // invalid email
            "short", // invalid password
            "different" // passwords don't match
        );
        assertFalse(user.validate());
    }

    // Registration tests
    @Test
    void testRegister_Success() {
        registerUser user = createValidUser();
        //assertDoesNotThrow(user::register);
    }

    @Test
    void testRegister_Failure() {
        registerUser user = new registerUser(
            "inv", // invalid username
            "valid@example.com",
            "ValidPass123",
            "ValidPass123"
        );
        assertThrows(IllegalArgumentException.class, user::register);
    } */
}