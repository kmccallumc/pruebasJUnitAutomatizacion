
package viu41giin;

import java.util.regex.Pattern;

/** KMC: clase para registrar a un usuario con validacion de nombres de usuario, email, contraseña 
 *  Clase constuida con ejemplos clasicos de Registro de Usuario
 **/

public class registerUser {
    // User 
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    
    // Constantes para validacion
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 12;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    /** Constructor para registerUser **/
    public registerUser(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
    /** Valida que todos los campos necesarios para crear el nuevo usuario son correctos **/
    public boolean validate() {
        return isValidUsername() && isValidEmail() && isValidPassword() && passwordsMatch();
    }
    
    /** Validacion de nombre de usuario **/
    public boolean isValidUsername() {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        // Valida longitud del nombre de usuario
        if (username.length() < MIN_USERNAME_LENGTH || username.length() > MAX_USERNAME_LENGTH) {
            return false;
        }
        
        // valida caracteres
        return username.matches("^[a-zA-Z0-9_]+$");
    }
    
    /** Validacion del email  **/
    public boolean isValidEmail() {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    
     /** Validacion del password  **/
    public boolean isValidPassword() {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        
        // Longitud minima
        if (password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        
        // valida requisitos para contraseña que debe tener: una minuscula + una mayuscula + un digito
        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) hasDigit = true;
            if (Character.isLowerCase(c)) hasLower = true;
            if (Character.isUpperCase(c)) hasUpper = true;
        }
        
        return hasDigit && hasLower && hasUpper;
    }
    
     /** Validacion de la confirmacion del password  **/
    public boolean passwordsMatch() {
        if (password == null || confirmPassword == null) {
            return false;
        }
        return password.equals(confirmPassword);
    }
    
    /** Registra al usuario en una BBDD Ficticia, siempre y cuando la validacion sea correcta **/
    public boolean register() {
        return validate();   
    }
    
    // Getters y Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}