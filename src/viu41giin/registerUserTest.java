package viu41giin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
//import viu41giin.registerUser;

public class registerUserTest {

    static registerUser[] usuVal = new registerUser[10];
    static registerUser usuInval, usuInvalToLong, usuInvalNullpass, usuInvalPassMismatch;

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
        usuInvalToLong = new registerUser("estenombredeusuarioesdemasiadolargo", "valid@example.com", "ValidPass123", "ValidPass123");
        usuInvalNullpass = new registerUser("validUser123", "valid@example.com", null, null);
        usuInvalPassMismatch = new registerUser("validUser123", "valid@example.com", "ValidPass123", "ValidPass132" );

        // validacion extra que 
        assertEquals("Comprobamos el numero de usuarios validos", 10, usuVal.length);
    }

    // Ahora empiezo a validar 

    // Validacion 1: Email validation tests
    @Test
    public void testEmail_Valid() {
       for (registerUser ru: usuVal){
           assertTrue("Comprobamos que los emails son correctos", ru.isValidEmail());
       }
        
    }

    // validacion 2: nombre de usuario muy largo
    @Test
    public void testUsername_TooLong() {
        assertFalse("Comprobamos que no pueda tener un usuario más largo de lo permitido",usuInvalToLong.isValidUsername());
    }

    // validacion 3: nombre de usuario invalido
    @Test
    public void testUsername_invalid() {
        assertFalse("Comprobamos que no permita caracteres invalidos", usuInval.isValidUsername());
    }

   // validacion  4: null password
   @Test
   public void testPassword_Null() {  
       assertFalse("Comprobamos que no pueda tener un password vacio",usuInvalNullpass.isValidPassword());
   }

     @Test
    public void testPasswordsDontMatch() {
        assertFalse("Comprobamos que la confirmacion del password sea correcta",usuInvalPassMismatch.passwordsMatch());
    }

    // validacion total
    @Test
    public void testValidUserRegistration() {
        assertTrue("Usuario creado correctamente", usuVal[0].validate());
    }

       @AfterClass
    public static void afterClass() {
        System.out.println("afterClass()");
        for (int i = 0; i < 10; i++) {
            usuVal[i] = null;
        }
        assertEquals("Comprobamos que hemos limpiado el array", null,
                usuVal[5]);
    }
}