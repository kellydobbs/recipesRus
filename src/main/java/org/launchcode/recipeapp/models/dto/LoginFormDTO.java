package org.launchcode.recipeapp.models.dto;

import javax.naming.LinkLoopException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @Size(min = 6, max = 36, message = "Email required")
    private String email;


    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;

    @Pattern(regexp = "[1-3]")
    private String access;

    public LoginFormDTO() {
    }
        public String getUsername() { return username; }
        public String getEmail() { return email;}

        public String getAccess(){
        return access;
        }

        public void setUsername (String username){
            this.username = username;
        }

        public void setAccess(){
        this.access = access;
        }

        public void setEmail(String email) { this.email = email; }

    public String getPassword () { return password; }

        public void setPassword (String password){ this.password = password; }

    }
