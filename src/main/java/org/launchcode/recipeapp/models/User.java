package org.launchcode.recipeapp.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Entity
public class User extends AbstractEntity {

   /* @updated AH*/

   private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


   @NotBlank(message = "Username is required")
   private String username;

   @NotBlank(message = "Email is required")
   private String email;

   @NotBlank(message = "Password is required")
   private String pwHash;

   private String access;


   @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
   private List<UserRecipe> recipes = new ArrayList<>();


   public User() {
   }

   public User(String username, String password, String access, String email) {
      this.username = username;
      this.pwHash = encoder.encode(password);
      this.access = access;
      this.email = email;
   }

   public String getAccess(){
      return access;
   }

   public String getUsername() {
      return username;
   }

   public String getEmail() { return email; }

   public boolean isMatchingPassword(String password) {
      return encoder.matches(password, pwHash);
   }

   public List<UserRecipe> getRecipes() {
      return recipes;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setAccess(String access) {
      this.access = access;
   }

   public void setEmail(String email) { this.email = email; }

   public void setRecipes(List<UserRecipe> recipes) {
      this.recipes = recipes;
   }

}
