package org.launchcode.recipeapp.models.dto;

import com.fasterxml.jackson.core.SerializableString;

public class RegistrationFormDTO extends LoginFormDTO {

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }



}