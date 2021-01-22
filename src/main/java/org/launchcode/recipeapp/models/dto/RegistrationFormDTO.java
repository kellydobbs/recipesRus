package org.launchcode.recipeapp.models.dto;

import com.fasterxml.jackson.core.SerializableString;

public class RegistrationFormDTO extends LoginFormDTO {

    private String verifyEmail;

    private String access;

    @Override
    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getVerifyEmail() {return verifyEmail;}

    public void setVerifyEmail(String verifyEmail) { this.verifyEmail = verifyEmail;}

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }



}